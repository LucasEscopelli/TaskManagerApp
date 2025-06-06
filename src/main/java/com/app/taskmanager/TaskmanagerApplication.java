package com.app.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.app.taskmanager.model"})
public class TaskmanagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskmanagerApplication.class, args);
  }
}
