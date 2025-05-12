package com.app.taskmanager.service.processors.assemblers;

import com.app.taskmanager.api.dto.TaskRecordDto;
import com.app.taskmanager.model.TaskRecord;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TaskHistoryAssembler implements Function<List<TaskRecord>, List<TaskRecordDto>> {
  @Autowired TaskRecordDtoAssembler taskRecordDtoAssembler;

  @Override
  public List<TaskRecordDto> apply(List<TaskRecord> taskRecords) {
    return taskRecords.stream()
        .map(taskRecord -> taskRecordDtoAssembler.apply(taskRecord))
        .toList();
  }
}
