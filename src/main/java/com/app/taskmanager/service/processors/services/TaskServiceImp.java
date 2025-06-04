package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.User;
import com.app.taskmanager.service.nullobjects.NullTask;
import com.app.taskmanager.service.processors.updater.TaskUpdater;
import com.app.taskmanager.service.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TaskServiceImp {

  @Autowired TaskRepository taskRepository;
  @Autowired UserServiceImp userService;

  public void createTask(Task newTask) {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    User userOfRequest = userService.getUserByLogin((String) authentication.getPrincipal());
    newTask.setOwnerUser(userOfRequest);
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
