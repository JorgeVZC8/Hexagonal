package com.openwebinars.hexagonal.infrastructure.web.rest.task;

import com.openwebinars.hexagonal.application.usecase.task.edit.CompleteTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.task.edit.EditTaskUseCase;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskPutController {

    private final EditTaskUseCase editTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;

    @PutMapping("/{id}/complete")
    public TaskResponse completeTask(@PathVariable Long id) {
        Task t = completeTaskUseCase.completeTask(TaskId.of(id));
        return TaskMapper.toResponse(t);
    }

    @PutMapping("/{id}")
    public TaskResponse editTask(@PathVariable Long id, @RequestBody TaskEditRequest taskEditRequest) {
        Task t = editTaskUseCase.update(TaskMapper.toCommand(id, taskEditRequest));
        return TaskMapper.toResponse(t);
    }
}