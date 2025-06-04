package com.app.taskmanager.controller;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.processors.services.TaskServiceImp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired TaskServiceImp taskServiceImp;

  @GetMapping("/{id}")
  public Task getTaskById(@PathVariable Long id) {
    return taskServiceImp.getTaskById(id);
  }

  @GetMapping
  public List<Task> getTasksByUserId(@RequestParam Long userId) {
    return taskServiceImp.getTasksByUserId(userId);
  }

  @PostMapping
  public void createTask(@RequestBody Task newTask) {
    taskServiceImp.createTask(newTask);
  }

  @PutMapping("/{id}")
  public void updateTask(@RequestParam Long id, @RequestBody Task taskUpdated) {
    taskServiceImp.updateTask(id, taskUpdated);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@RequestParam Long id) {
    taskServiceImp.deleteTask(id);
  }
}
