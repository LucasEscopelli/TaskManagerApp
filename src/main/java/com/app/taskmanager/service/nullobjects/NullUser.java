package com.app.taskmanager.service.nullobjects;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.User;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.List;

public class NullUser extends User {

  public Long getUserId() {
    return (long) -1;
  }

  public String getName() {
    return "User Not Available";
  }

  public List<Task> getTasks() {
    return Collections.emptyList();
  }
}
