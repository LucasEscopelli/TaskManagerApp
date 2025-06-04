package com.app.taskmanager.service.processors.services;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.nullobjects.NullUser;
import com.app.taskmanager.service.processors.services.interfaces.UserServiceDef;
import com.app.taskmanager.service.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserServiceDef {

  @Autowired UserRepository userRepository;

  @Override
  public void createUser(User newUser) {
    userRepository.save(newUser);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(new NullUser());
  }

  @Override
  public void updateUser(Long id, User userFromRequest) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

    user.setName(userFromRequest.getName());
    var authentication = SecurityContextHolder.getContext().getAuthentication();

    userRepository.save(user);
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public User getUserByLogin(String login) {
    return userRepository.findByLogin(login).orElseThrow();
  }
}
