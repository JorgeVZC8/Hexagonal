package com.openwebinars.hexagonal.infrastructure.web.dto.user;

public record UserRequest(String email, String name, String password) {
}
