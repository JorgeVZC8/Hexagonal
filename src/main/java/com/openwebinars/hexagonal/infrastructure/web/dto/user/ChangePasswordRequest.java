package com.openwebinars.hexagonal.infrastructure.web.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(@NotBlank(message = "La nueva contraseña no puede estar vacia") @Size(min = 8, message = "La nueva contraseña debe tener al menos 8 caracteres") String newPassword) {
}
