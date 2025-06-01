package com.app.taskmanager.service.repositories;

import com.app.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerUserUserId(Long userId);
}
