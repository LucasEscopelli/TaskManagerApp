package com.app.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
@Table(
    name = "account",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  Long accountId;

  @Column(nullable = false, unique = true)
  String login;

  @Column(nullable = false)
  String password;
}
