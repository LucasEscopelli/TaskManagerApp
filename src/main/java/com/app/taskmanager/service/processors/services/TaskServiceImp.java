package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.nullobjects.NullTask;
import com.app.taskmanager.service.processors.updater.TaskUpdater;
import com.app.taskmanager.service.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskServiceImp {
    TaskRepository taskRepository;

    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElse(new NullTask());
    }

    public List<Task> getTasksByUserId(Long userId){
        return taskRepository.findByOwnerUserUserId(userId);
    }

    public void updateTask(Long taskId, Task taskUpdated){
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        optionalTask.ifPresent(task -> {
            TaskUpdater.updateTaskFromDto(task, taskUpdated);
        });

        Task newTask = optionalTask.orElse(taskUpdated);
        taskRepository.save(newTask);
    }

    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
    }
}
