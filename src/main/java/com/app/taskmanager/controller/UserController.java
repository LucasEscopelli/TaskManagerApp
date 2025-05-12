package com.app.taskmanager.controller;

import com.app.taskmanager.api.dto.UserDto;
import com.app.taskmanager.service.processors.services.interfaces.UserServiceDef;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/users")
public class UserController {

  UserServiceDef userService;

  @GetMapping("/{id}")
  public UserDto getUser(Long userId) {
    return userService.getUserById(userId);
  }
}
