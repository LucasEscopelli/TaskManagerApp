package com.app.taskmanager.controller;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.processors.services.interfaces.UserServiceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController("/users")
public class UserController {

  @Autowired
  UserServiceDef userService;

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long userId) {
    return userService.getUserById(userId);
  }

  @PostMapping
  public void createUser(User newUser) {
    userService.createUser(newUser);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable Long id, User updatedUser) {
    userService.updateUser(id, updatedUser);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }
}
