package com.app.taskmanager.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.app.taskmanager.service.security.login.JwtAuthenticationFilter;
import com.app.taskmanager.service.security.login.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Test
  void testLoginEndpointIsPermittedWithoutAuth() throws Exception {
    mockMvc.perform(get("/auth/login")).andExpect(status().isOk());
  }

  @Test
  void testProtectedEndpointRequiresAuth() throws Exception {
    mockMvc.perform(get("/tasks")).andExpect(status().is4xxClientError());
  }

  @Test
  void testProtectedEndpointWithValidToken() throws Exception {
    String token = TokenUtils.generateToken("testuser"); // Método hipotético, ou mock

    mockMvc
        .perform(
            get("/tasks")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
