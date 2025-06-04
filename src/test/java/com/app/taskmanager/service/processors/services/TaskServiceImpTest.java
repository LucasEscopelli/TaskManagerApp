package com.app.taskmanager.service.processors.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.nullobjects.NullTask;
import com.app.taskmanager.service.processors.updater.TaskUpdater;
import com.app.taskmanager.service.repositories.TaskRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImpTest {

  @Mock private TaskRepository taskRepository;

  @Mock private TaskUpdater taskUpdater;

  @InjectMocks private TaskServiceImp taskServiceImp;

  @Test
  void testGetTaskByIDNotFound() {
    Task task = new Task();
    task.setTaskId(1L);

    when(taskRepository.findById(1L)).thenReturn(Optional.empty());

    Task result = taskServiceImp.getTaskById(1L);

    assertInstanceOf(NullTask.class, result);
  }

  @Test
  void testGetTasksByUserID() {
    Long userId = 1L;

    Task task1 = new Task();
    Task task2 = new Task();
    task1.setTaskId(1L);
    task1.setTaskId(2L);

    List<Task> tasks = Arrays.asList(task1, task2);

    when(taskRepository.findByOwnerUserUserId(userId)).thenReturn(tasks);

    List<Task> result = taskServiceImp.getTasksByUserId(userId);

    assertEquals(tasks, result);
  }

  @Test
  void testUpdateTaskFound() {
    Task oldTask = new Task();
    oldTask.setTaskId(1L);
    oldTask.setTaskDescription("old description");

    Task newTask = new Task();
    newTask.setTaskId(1L);
    newTask.setTaskDescription("new description");

    when(taskRepository.findById(1L)).thenReturn(Optional.of(oldTask));

    taskServiceImp.updateTask(1L, newTask);

    assertEquals("new description", oldTask.getTaskDescription());
    verify(taskRepository, times(1)).save(oldTask);
  }

  @Test
  void testUpdateTaskNotFound() {
    Task notFoundTask = new Task();
    notFoundTask.setTaskId(1L);

    when(taskRepository.findById(1L)).thenReturn(Optional.empty());

    taskServiceImp.updateTask(1L, notFoundTask);

    verify(taskRepository, times(1)).save(notFoundTask);
  }

  @Test
  void testDeleteTask() {
    taskServiceImp.deleteTask(1L);

    verify(taskRepository, times(1)).deleteById(1L);
  }
}
