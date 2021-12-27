package io.course.jk99.logic;

import io.course.jk99.TaskConfigurationProperties;
import io.course.jk99.model.ProjectRepository;
import io.course.jk99.model.TaskGroupRepository;
import io.course.jk99.model.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    ProjectService projectService(
            ProjectRepository repository,
            TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            TaskConfigurationProperties config){
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    TaskGroupService taskGroupService(
            final TaskGroupRepository taskGroupRepository,
            final TaskRepository taskRepository)
    {
        return new TaskGroupService(taskGroupRepository, taskRepository);
    }


}
