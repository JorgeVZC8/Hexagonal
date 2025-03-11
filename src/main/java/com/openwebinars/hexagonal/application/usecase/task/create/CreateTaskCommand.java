package com.openwebinars.hexagonal.application.usecase.task.create;

import com.openwebinars.hexagonal.application.validation.SelfValidating;
import com.openwebinars.hexagonal.domain.UserId;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class CreateTaskCommand extends SelfValidating<CreateTaskCommand> {

    @NotBlank(message = "title cannot be blank")
    private final String title;

    @NotBlank(message = "description cannot be blank")
    private final String description;

    private final UserId author;

    @Builder
    public CreateTaskCommand(String title, String description, UserId author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.validateSelf();
    }


}
