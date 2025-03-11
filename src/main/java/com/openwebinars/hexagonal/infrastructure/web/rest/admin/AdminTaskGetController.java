package com.openwebinars.hexagonal.infrastructure.web.rest.admin;

import com.openwebinars.hexagonal.application.service.FindTaskService;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.UserTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/task")
@RequiredArgsConstructor
public class AdminTaskGetController {

    private final FindTaskService findTaskService;

    @GetMapping
    public List<UserTaskResponse> allTask(){
        return findTaskService.findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }
}
