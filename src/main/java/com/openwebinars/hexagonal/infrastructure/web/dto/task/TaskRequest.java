package com.openwebinars.hexagonal.infrastructure.web.dto.task;

import com.openwebinars.hexagonal.domain.UserId;

public record TaskRequest(String title, String description) {

}
