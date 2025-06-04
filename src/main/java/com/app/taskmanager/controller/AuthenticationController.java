package com.app.taskmanager.controller;

import com.app.taskmanager.service.security.login.AuthenticationService;
import com.app.taskmanager.service.security.login.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired private AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    try {
      String token =
          authenticationService.authenticate(
              loginRequest.getUsername(), loginRequest.getPassword());
      return ResponseEntity.ok(token);
    } catch (RuntimeException ex) {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
  }
}
