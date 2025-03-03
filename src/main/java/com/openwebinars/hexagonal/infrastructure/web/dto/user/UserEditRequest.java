package com.openwebinars.hexagonal.infrastructure.web.dto.user;

import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserEditRequest (@NotBlank (message="El nombre no puede estar vacio") String name, @Email(message="El email tiene que ser valido") String email, @NotNull(message="El role no puede ser nulo") UserRole role){
}
