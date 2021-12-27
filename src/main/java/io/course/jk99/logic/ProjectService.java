package io.course.jk99.logic;

import io.course.jk99.TaskConfigurationProperties;
import io.course.jk99.model.*;
import io.course.jk99.model.projection.GroupReadModel;
import io.course.jk99.model.projection.GroupTaskWriteModel;
import io.course.jk99.model.projection.GroupWriteModel;
import io.course.jk99.model.projection.ProjectWriteModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectService {

    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;
    private TaskConfigurationProperties config;
    private TaskGroupService taskGroupService;

    public ProjectService(final ProjectRepository repository, final TaskGroupRepository taskGroupRepository, TaskGroupService taskGroupService, final TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.taskGroupService = taskGroupService;
    }


    // odczyt i zapis

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(final ProjectWriteModel toSave) {
        return repository.save(toSave.toProject());
    }

    //tworzenie grupy

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProject_Id(projectId)) {
            //wyjątek dla konfiguracji task.template.allowMultipleTasks=false
            throw new IllegalStateException("Only one undone group from project is allowed.");
        }

        return repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    //wyliczanie deadline'u
                    targetGroup.setTasks(
                            project.getSteps().stream()
                                .map(step -> {
                                    var task = new GroupTaskWriteModel();
                                    task.setDescription(step.getDescription());
                                    task.setDeadline(deadline.plusDays(step.getDaysToDeadline()));
                                    return task;
                                }
                                ).collect(Collectors.toList()));
                    return taskGroupService.createGroup(targetGroup, project);
                }).orElseThrow(() -> new IllegalArgumentException("Project with given id not found"));
    }



}
