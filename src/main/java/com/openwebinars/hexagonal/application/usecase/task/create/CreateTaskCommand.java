package com.openwebinars.hexagonal.application.usecase.task.create;

import com.openwebinars.hexagonal.domain.UserId;

public record CreateTaskCommand(String title, String description, UserId author) {
}
