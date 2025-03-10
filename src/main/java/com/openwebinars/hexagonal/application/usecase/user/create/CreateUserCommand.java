package com.openwebinars.hexagonal.application.usecase.user.create;

import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;

public record CreateUserCommand(String name, String email, String Password, UserRole role) {
}
