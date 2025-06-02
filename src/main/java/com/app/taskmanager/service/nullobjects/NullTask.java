package com.app.taskmanager.service.nullobjects;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.TaskRecord;
import com.app.taskmanager.model.User;
import com.app.taskmanager.model.enums.Status;
import java.util.Collections;
import java.util.List;

public class NullTask extends Task {

    public Long getTaskId() {
        return -1L;
    }

    public String getTaskDescription() {
        return "No description available";
    }

    public Status getTaskStatus() {
        return Status.UNKNOWN;
    }

    public List<TaskRecord> getTaskHistory() {
        return Collections.emptyList();
    }

    public User getOwnerUser() {
        return (User) new NullUser();
    }
}
