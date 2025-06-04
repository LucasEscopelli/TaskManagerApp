package com.app.taskmanager.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  Long userId;

  String name;

  @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  List<Task> tasks;

  @ManyToOne Account lastModifiedBy;
}
