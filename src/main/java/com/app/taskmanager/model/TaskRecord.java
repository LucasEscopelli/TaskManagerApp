package com.app.taskmanager.model;

import com.app.taskmanager.model.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskRecordId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    Task task;

    @Enumerated(EnumType.STRING)
    Status status;

    @Temporal(TemporalType.TIMESTAMP)
    Date recordDate;

    @ManyToOne
    @JoinColumn(name = "responsable_user_id", nullable = false)
    User responsableUser;
}
