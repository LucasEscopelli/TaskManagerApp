package com.app.taskmanager.controller;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.processors.services.TaskServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/tasks")
public class TaskController {
    TaskServiceImp taskServiceImp;

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id){
        return taskServiceImp.getTaskById(id);
    }

    @GetMapping
    public List<Task> getTasksByUserId(@RequestParam Long assignedTo){
        return null;
    }

    @PostMapping
    public void createTask(Task newTask){

    }

    @PutMapping("/{id}")
    public void updateTask(@RequestParam Long id, Task taskUpdated){

    }

    @DeleteMapping("/{id}")
    public void deleteTask(@RequestParam Long id){

    }


}
