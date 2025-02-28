package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.UserId;

public record EditUserCommand (UserId id, String name, String email, String password){
}
