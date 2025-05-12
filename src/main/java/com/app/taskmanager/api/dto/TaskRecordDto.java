package com.app.taskmanager.api.dto;

import com.app.taskmanager.model.enums.Status;
import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskRecordDto {
  Status status;

  Date recordDate;

  UserDto responsableUser;
}
