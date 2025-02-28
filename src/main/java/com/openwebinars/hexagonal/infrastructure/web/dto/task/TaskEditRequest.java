package com.openwebinars.hexagonal.infrastructure.web.dto.task;

public record TaskEditRequest(
        String title, String description, boolean complete
) {
}
