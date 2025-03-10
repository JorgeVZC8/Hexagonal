package com.openwebinars.hexagonal.application.usecase.task.find;

import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.TaskNotFoundException;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindTaskUseCase {

    private final TaskRepository taskRepository;

    public Task findById(TaskId id){
        return taskRepository.getById(id)
                .orElseThrow(()-> new TaskNotFoundException(id));
    }

    public List<Task> findAll(){

        List<Task> result = taskRepository.getAll();

        if(result.isEmpty()){
            throw new TaskNotFoundException();
        }

        return result;
    }

    public List<Task> findAllByUserId(UserId id){
        List<Task> tasks= taskRepository.getByUserId(id);

        if (tasks.isEmpty()){
            throw new TaskNotFoundException();
        }

        return tasks;
    }

}
