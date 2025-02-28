package com.openwebinars.hexagonal.infrastructure.db.repos.jpa;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRapositoryJpa extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);
}
