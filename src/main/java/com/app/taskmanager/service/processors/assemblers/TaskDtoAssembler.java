package com.app.taskmanager.service.processors.assemblers;

import com.app.taskmanager.api.dto.TaskDto;
import com.app.taskmanager.model.Task;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskDtoAssembler implements Function<Task, TaskDto> {
  @Autowired UserDtoAssembler userDtoAssembler;
  @Autowired TaskHistoryAssembler taskHistoryAssembler;

  @Override
  public TaskDto apply(Task task) {
    return new TaskDto(
        task.getTaskDescription(),
        task.getTaskStatus(),
        taskHistoryAssembler.apply(task.getTaskHistory()),
        userDtoAssembler.apply(task.getOwnerUser()));
  }
}
