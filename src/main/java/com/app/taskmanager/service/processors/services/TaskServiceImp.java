package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.nullobjects.NullTask;
import com.app.taskmanager.service.processors.updater.TaskUpdater;
import com.app.taskmanager.service.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImp {

  @Autowired TaskRepository taskRepository;

  public void createTask(Task newTask) {
    taskRepository.save(newTask);
  }

  public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElse(new NullTask());
  }

  public List<Task> getTasksByUserId(Long userId) {
    return taskRepository.findByOwnerUserUserId(userId);
  }

  public void updateTask(Long taskId, Task taskUpdated) {
    Optional<Task> optionalTask = taskRepository.findById(taskId);

    optionalTask.ifPresent(
        task -> {
          TaskUpdater.updateTask(task, taskUpdated);
        });

    Task newTask = optionalTask.orElse(taskUpdated);
    taskRepository.save(newTask);
  }

  public void deleteTask(Long taskId) {
    taskRepository.deleteById(taskId);
  }
}
