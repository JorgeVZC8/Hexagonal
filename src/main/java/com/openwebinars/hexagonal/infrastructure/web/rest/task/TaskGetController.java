package com.openwebinars.hexagonal.infrastructure.web.rest.task;

import com.openwebinars.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskGetController {

    private final FindTaskUseCase findTaskUseCase;

    @GetMapping
    public List<TaskResponse> allTask(){
        return findTaskUseCase.findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse taskById(@PathVariable Long id){
        Task t= findTaskUseCase.findById(TaskId.of(id));
        return TaskMapper.toResponse(t);
    }
}
