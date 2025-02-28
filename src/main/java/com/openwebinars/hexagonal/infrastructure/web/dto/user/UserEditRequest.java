package com.openwebinars.hexagonal.infrastructure.web.dto.user;

import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;

public record UserEditRequest (String name, String email, UserRole role){
}
