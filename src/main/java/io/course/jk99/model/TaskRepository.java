package io.course.jk99.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();

    Optional<Task> findById(Integer id);

    Task save(Task entity);

    Page<Task> findAll(Pageable page);

    boolean existsById(Integer id);

    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    List<Task> findByDone(@Param("state") boolean done);

    List<Task> findAllByGroup_Id(Integer groupId);
}
