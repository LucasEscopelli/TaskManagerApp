package com.app.taskmanager.service.processors.assemblers;

import com.app.taskmanager.api.dto.TaskDto;
import com.app.taskmanager.model.User;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserTasksDtoAssembler implements Function<User, List<TaskDto>> {
  @Override
  public List<TaskDto> apply(User user) {
    return List.of();
  }
}
