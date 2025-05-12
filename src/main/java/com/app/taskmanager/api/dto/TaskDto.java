package com.app.taskmanager.api.dto;

import com.app.taskmanager.model.enums.Status;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskDto {
  String taskDescription;

  Status taskStatus;

  List<TaskRecordDto> taskHistory;

  UserDto ownerUser;
}
