package com.openwebinars.hexagonal.infrastructure.db.repos.jpa;

import com.openwebinars.hexagonal.infrastructure.db.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskEntityRepositoryJpa extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByAuthor(UUID id);
}
