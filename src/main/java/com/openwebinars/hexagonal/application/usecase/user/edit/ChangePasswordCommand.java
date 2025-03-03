package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.UserId;

public record ChangePasswordCommand(UserId id, String newPassword) {
}
