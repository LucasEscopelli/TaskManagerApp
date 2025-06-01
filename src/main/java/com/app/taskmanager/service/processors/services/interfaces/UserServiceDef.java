package com.app.taskmanager.service.processors.services.interfaces;

import com.app.taskmanager.model.User;

public interface UserServiceDef {
  void createUser(User newUser);

  User getUserById(Long id);

  void updateUser(Long id, User userFromRequest);

  void deleteUser(Long id);
}
