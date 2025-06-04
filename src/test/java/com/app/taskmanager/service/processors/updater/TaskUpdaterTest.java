package com.app.taskmanager.service.processors.updater;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.model.User;
import com.app.taskmanager.model.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskUpdaterTest {

  @Test
  void testUpdateTaskFromDto_bothTaskNull() {

    Exception ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              TaskUpdater.updateTask(null, null);
            });

    assertEquals("Task and taskUpdated must not be null", ex.getMessage());
  }

  @Test
  void testUpdateTaskFromDto_taskOriginalNull() {

    Task taskUpdated = new Task();

    Exception ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              TaskUpdater.updateTask(null, taskUpdated);
            });

    assertEquals("Task and taskUpdated must not be null", ex.getMessage());
  }

  @Test
  void testUpdateTaskFromDto_updatedTaskNull() {

    Task taskOriginal = new Task();

    Exception ex =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              TaskUpdater.updateTask(taskOriginal, null);
            });

    assertEquals("Task and taskUpdated must not be null", ex.getMessage());
  }

  @Test
  void testUpdateTask_success() {
    User oldUser = new User();
    Task taskOriginal = new Task();

    User newUser = new User();
    Task taskUpdated = new Task();

    taskOriginal.setTaskDescription("old description");
    taskOriginal.setTaskStatus(Status.IN_PROGRESS);
    taskOriginal.setOwnerUser(oldUser);

    taskUpdated.setTaskDescription("new description");
    taskUpdated.setTaskStatus(Status.COMPLETED);
    taskUpdated.setOwnerUser(newUser);

    TaskUpdater.updateTask(taskOriginal, taskUpdated);

    assertEquals("new description", taskOriginal.getTaskDescription());
    assertEquals(Status.COMPLETED, taskOriginal.getTaskStatus());
    assertEquals(newUser, taskOriginal.getOwnerUser());
  }
}
