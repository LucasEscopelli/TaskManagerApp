package com.app.taskmanager.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.app.taskmanager.model.User;
import com.app.taskmanager.service.processors.services.interfaces.UserServiceDef;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockBean private UserServiceDef userService;

  @Autowired private ObjectMapper objectMapper;

  private User user;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setUserId(1L);
    user.setName("John Doe");
  }

  @Test
  void testGetUser() throws Exception {
    when(userService.getUserById(1L)).thenReturn(user);

    mockMvc
        .perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(
            jsonPath("$.userId").value(user.getUserId())) // Veja abaixo sobre o "userId" vs "id"
        .andExpect(jsonPath("$.name").value(user.getName()));
  }

  @Test
  void testCreateUser() throws Exception {
    doNothing().when(userService).createUser(any(User.class));

    mockMvc
        .perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());
  }

  @Test
  void testUpdateUser() throws Exception {
    doNothing().when(userService).updateUser(eq(1L), any(User.class));

    mockMvc
        .perform(
            put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());
  }

  @Test
  void testDeleteUser() throws Exception {
    doNothing().when(userService).deleteUser(1L);

    mockMvc.perform(delete("/users/1")).andExpect(status().isOk());
  }
}
