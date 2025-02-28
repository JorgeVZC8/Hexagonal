package com.openwebinars.hexagonal.application.usecase.task.delete;

import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public void delete(Task t){
        deleteById(t.getId());
    }

    public void deleteById(TaskId id) {
        taskRepository.delete(id);
    }

}
