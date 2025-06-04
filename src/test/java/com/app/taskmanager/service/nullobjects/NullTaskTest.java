package com.app.taskmanager.service.nullobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.app.taskmanager.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NullTaskTest {

  @InjectMocks private NullTask nullTask;

  @Test
  void testGetTaskId() {
    assertEquals(-1L, nullTask.getTaskId());
  }

  @Test
  void testGetTaskStatus() {
    assertEquals(Status.UNKNOWN, nullTask.getTaskStatus());
  }

  @Test
  void testGetTaskDescription() {
    assertEquals("No description available", nullTask.getTaskDescription());
  }

  @Test
  void testGetOwnerUser() {
    assertInstanceOf(NullUser.class, nullTask.getOwnerUser());
  }
}
