package com.app.taskmanager.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.app.taskmanager.model.Task;
import com.app.taskmanager.service.processors.services.TaskServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private TaskServiceImp taskServiceImp;

  private Task task;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    task = new Task();
    task.setTaskId(1L);
    task.setTaskDescription("Test Description");

    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetTaskById() throws Exception {
    when(taskServiceImp.getTaskById(1L)).thenReturn(task);

    mockMvc
        .perform(get("/tasks/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.taskId").value(task.getTaskId()))
        .andExpect(jsonPath("$.taskDescription").value(task.getTaskDescription()));
  }

  @Test
  void testGetTasksByUserId() throws Exception {
    List<Task> tasks = Arrays.asList(task);
    when(taskServiceImp.getTasksByUserId(1L)).thenReturn(tasks);

    mockMvc
        .perform(get("/tasks").param("userId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1));
  }

  @Test
  void testCreateTask() throws Exception {
    mockMvc
        .perform(
            post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
        .andExpect(status().isOk());

    verify(taskServiceImp, times(1)).createTask(any(Task.class));
  }

  @Test
  void testUpdateTask() throws Exception {
    mockMvc
        .perform(
            put("/tasks/1")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
        .andExpect(status().isOk());

    verify(taskServiceImp, times(1)).updateTask(eq(1L), any(Task.class));
  }

  @Test
  void testDeleteTask() throws Exception {
    mockMvc.perform(delete("/tasks/1").param("id", "1")).andExpect(status().isOk());

    verify(taskServiceImp, times(1)).deleteTask(1L);
  }
}
