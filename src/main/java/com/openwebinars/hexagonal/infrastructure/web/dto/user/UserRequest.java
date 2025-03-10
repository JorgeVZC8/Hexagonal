package com.openwebinars.hexagonal.infrastructure.web.dto.user;

import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;

public record UserRequest(String email, String name, String password, UserRole role) {
}
