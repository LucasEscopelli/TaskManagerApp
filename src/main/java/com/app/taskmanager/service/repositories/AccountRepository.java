package com.app.taskmanager.service.repositories;

import com.app.taskmanager.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByLogin(String login);
}
