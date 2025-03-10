package com.openwebinars.hexagonal.application.usecase.task.create;

import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public Task create(CreateTaskCommand command){
        Task t = Task.builder()
                .title(command.title())
                .description(command.description())
                .createdAt(LocalDateTime.now())
                .complete(false)
                .author(command.author())
                .build();
        return taskRepository.save(t);
    }
}
