package com.openwebinars.hexagonal.infrastructure.web.rest.task;


import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import com.openwebinars.hexagonal.infrastructure.security.model.AuthUser;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskPostController {
    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest, @AuthenticationPrincipal AuthUser user){
        CreateTaskCommand command= TaskMapper.toCommand(taskRequest,user.getIdAsUserId());
        Task t= createTaskUseCase.create(command);
        return ResponseEntity.status(201).body(TaskMapper.toResponse(t));
    }
}
