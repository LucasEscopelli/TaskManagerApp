package com.app.taskmanager.service.security.login;

import static com.app.taskmanager.service.security.login.TokenUtils.generateToken;

import com.app.taskmanager.model.Account;
import com.app.taskmanager.service.processors.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  @Autowired AccountService accountService;

  public String authenticate(String username, String password) {
    Account account = accountService.getAccountByLogin(username);
    if (account.getPassword().equals(password)) {
      throw new RuntimeException("Invalid credentials");
    }

    return generateToken(username);
  }
}
