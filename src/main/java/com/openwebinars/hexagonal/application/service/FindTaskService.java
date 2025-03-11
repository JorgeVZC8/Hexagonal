package com.openwebinars.hexagonal.application.service;

import com.openwebinars.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.openwebinars.hexagonal.domain.*;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FindTaskService {

    private final FindTaskUseCase findTaskUseCase;
    private final FindUserUseCase findUserUseCase;

    public UserTask findByTaskId(TaskId id){
        Task t= findTaskUseCase.findById(id);
        User u= findUserUseCase.findById(t.getAuthor());

        return new UserTask(u,t);
    }

    public List<UserTask> findAll(){
        List<Task> tasks = findTaskUseCase.findAll();
        List<UserId> authorIds = tasks
                .stream()
                .map(Task::getAuthor)
                .distinct()
                .toList();

        Map<UserId, List<User>> authors = findUserUseCase.getByIds(authorIds)
                .stream()
                .collect(Collectors.groupingBy(User::getId));

        return tasks.stream()
                .map(t-> new UserTask(authors.get(t.getAuthor()).get(0),t))
                .toList();
    }

    //Falta implementar el metodo para buscar todas las tareas por el id de un usuario.

}
