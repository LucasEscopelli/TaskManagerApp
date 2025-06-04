package com.app.taskmanager.model;

import com.app.taskmanager.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long taskId;

  String taskDescription;

  @Enumerated(EnumType.STRING)
  Status taskStatus;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = true)
  User ownerUser;
}
