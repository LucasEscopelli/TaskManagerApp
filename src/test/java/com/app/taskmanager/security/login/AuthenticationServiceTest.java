package com.app.taskmanager.security.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.processors.services.UserServiceImp;
import com.app.taskmanager.service.security.login.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
  @Mock private UserServiceImp userService;

  @InjectMocks private AuthenticationService authenticationService;

  private User mockUser;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockUser = new User();
    mockUser.setLogin("testuser");
    mockUser.setPassword("testpassword");
  }

  @Test
  void testAuthenticateSuccess() {
    when(userService.getUserByLogin("testuser")).thenReturn(mockUser);

    String token = authenticationService.authenticate("testuser", "testpassword");

    assertNotNull(token);
    assertTrue(token.length() > 10); // Pode ajustar dependendo do token gerado
  }

  @Test
  void testAuthenticateFailure() {
    when(userService.getUserByLogin("testuser")).thenReturn(mockUser);

    RuntimeException exception =
        assertThrows(
            RuntimeException.class,
            () -> {
              authenticationService.authenticate("testuser", "wrongpassword");
            });

    assertEquals("Invalid credentials", exception.getMessage());
  }
}
