package com.openwebinars.hexagonal.application.usecase.task.edit;

import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.error.TaskNotFoundException;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditTaskUseCase {

    private final TaskRepository taskRepository;

    public Task update(EditTaskCommand command){
        return taskRepository.getById(command.id())
                .map(t -> {
                    t.setTitle(command.title());
                    t.setDescription(command.description());
                    t.setComplete(command.complete());
                    return taskRepository.save(t);
                }).orElseThrow(()-> new TaskNotFoundException(command.id()));
    }
}
