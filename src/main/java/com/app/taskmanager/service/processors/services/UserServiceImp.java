package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.api.dto.UserDto;
import com.app.taskmanager.model.User;
import com.app.taskmanager.service.nullobjects.NullUser;
import com.app.taskmanager.service.processors.assemblers.UserDtoAssembler;
import com.app.taskmanager.service.processors.services.interfaces.UserServiceDef;
import com.app.taskmanager.service.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserServiceDef {

  UserRepository userRepository;
  UserDtoAssembler userDtoAssembler;

  @Override
  public void createUser(UserDto newUser) {

  }

  @Override
  public UserDto getUserById(Long id) {
    User userFromId = userRepository.findById(id).orElse(new NullUser());
    return userDtoAssembler.apply(userFromId);
  }

  @Override
  public void updateUser(Long id, UserDto userFromRequest) {}

  @Override
  public void deleteUser(Long id) {}
}
