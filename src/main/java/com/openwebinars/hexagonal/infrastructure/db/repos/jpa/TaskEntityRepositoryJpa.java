package com.openwebinars.hexagonal.infrastructure.db.repos.jpa;

import com.openwebinars.hexagonal.infrastructure.db.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskEntityRepositoryJpa extends JpaRepository<TaskEntity, Long> {
}
