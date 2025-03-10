package com.openwebinars.hexagonal.application.service;

import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTaskService {

    private final CreateTaskUseCase createTaskUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserTask createUserTask(CreateTaskCommand command){

        User u= findUserUseCase.findById(command.author());
        Task t= createTaskUseCase.create(command);

        return new UserTask(u,t);

    }
}
