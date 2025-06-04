package com.app.taskmanager.service.nullobjects;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.User;
import com.app.taskmanager.model.enums.Status;

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

  public User getOwnerUser() {
    return (User) new NullUser();
  }
}
