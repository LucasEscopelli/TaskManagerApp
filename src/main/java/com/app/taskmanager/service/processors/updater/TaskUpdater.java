package com.app.taskmanager.service.processors.updater;

import com.app.taskmanager.model.Task;

public class TaskUpdater {
    public static void updateTaskFromDto(Task task, Task taskUpdated) {
        if (task == null || taskUpdated == null) {
            throw new IllegalArgumentException("Task and taskUpdated must not be null");
        }
        task.setTaskDescription(taskUpdated.getTaskDescription());
        task.setTaskStatus(taskUpdated.getTaskStatus());

        task.setOwnerUser(taskUpdated.getOwnerUser());
    }
}
