package com.app.taskmanager.service.processors.assemblers;

import com.app.taskmanager.api.dto.UserDto;
import com.app.taskmanager.model.User;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDtoAssembler implements Function<User, UserDto> {
  @Autowired UserTasksDtoAssembler userTasksDtoAssembler;

  @Override
  public UserDto apply(User user) {
    return new UserDto(user.getName(), userTasksDtoAssembler.apply(user));
  }
}
