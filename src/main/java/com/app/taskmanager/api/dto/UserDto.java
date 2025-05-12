package com.app.taskmanager.api.dto;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDto {
  String name;

  List<TaskDto> tasks;
}
