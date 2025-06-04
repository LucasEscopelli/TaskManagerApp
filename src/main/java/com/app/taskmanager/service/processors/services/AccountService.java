package com.app.taskmanager.service.processors.services;

import static com.app.taskmanager.service.security.login.TokenUtils.parseToken;

import com.app.taskmanager.model.Account;
import com.app.taskmanager.service.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountService {
  @Autowired AccountRepository accountRepository;

  public Account getAccountByLogin(String login) {
    return accountRepository.findByLogin(login).orElseThrow();
  }

  public Account getAccountFromToken(String token) {
    String username = parseToken(token);
    return getAccountByLogin(username);
  }
}
