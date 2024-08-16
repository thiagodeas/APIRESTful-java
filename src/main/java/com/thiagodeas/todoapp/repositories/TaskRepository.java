package com.thiagodeas.todoapp.repositories;

import com.thiagodeas.todoapp.models.Task;
import com.thiagodeas.todoapp.models.projection.TaskProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<TaskProjection> findByUser_Id(Long id);
}
