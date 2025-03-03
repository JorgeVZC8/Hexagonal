package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;

public record EditUserCommand (UserId id, String name, String email, UserRole role){
}
