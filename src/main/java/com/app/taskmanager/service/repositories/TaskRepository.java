package com.app.taskmanager.service.repositories;

import com.app.taskmanager.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByOwnerUserUserId(Long userId);
}
