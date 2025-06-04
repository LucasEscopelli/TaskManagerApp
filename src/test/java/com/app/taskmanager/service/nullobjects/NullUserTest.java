package com.app.taskmanager.service.nullobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NullUserTest {
  @InjectMocks private NullUser nullUser;

  @Test
  void testGetUserId() {
    assertEquals(-1L, nullUser.getUserId());
  }

  @Test
  void testGetName() {
    assertEquals("User Not Available", nullUser.getName());
  }

  @Test
  void testGetTasks() {
    assertEquals(Collections.emptyList(), nullUser.getTasks());
  }
}
