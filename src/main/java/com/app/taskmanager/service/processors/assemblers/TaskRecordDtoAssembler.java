package com.app.taskmanager.service.processors.assemblers;

import com.app.taskmanager.api.dto.TaskRecordDto;
import com.app.taskmanager.model.TaskRecord;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskRecordDtoAssembler implements Function<TaskRecord, TaskRecordDto> {
  @Autowired UserDtoAssembler userDtoAssembler;

  @Override
  public TaskRecordDto apply(TaskRecord taskRecord) {
    return new TaskRecordDto(
        taskRecord.getStatus(),
        taskRecord.getRecordDate(),
        userDtoAssembler.apply(taskRecord.getResponsableUser()));
  }
}
