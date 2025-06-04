package com.app.taskmanager.service.security.login;

import static com.app.taskmanager.service.security.login.TokenUtils.generateToken;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.processors.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  @Autowired
  UserServiceImp userService;

  public String authenticate(String username, String password) {
    User user = userService.getUserByLogin(username);
    if (!user.getPassword().equals(password)) {
      throw new RuntimeException("Invalid credentials");
    }

    return generateToken(username);
  }
}
