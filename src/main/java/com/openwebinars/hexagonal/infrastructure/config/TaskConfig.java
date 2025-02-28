package com.openwebinars.hexagonal.infrastructure.config;

import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.task.delete.DeleteTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.task.edit.CompleteTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.task.edit.EditTaskCommand;
import com.openwebinars.hexagonal.application.usecase.task.edit.EditTaskUseCase;
import com.openwebinars.hexagonal.application.usecase.task.find.FindTaskUseCase;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import com.openwebinars.hexagonal.infrastructure.db.repos.impl.TaskRepositoryImpl;
import com.openwebinars.hexagonal.infrastructure.db.repos.jpa.TaskEntityRepositoryJpa;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TaskConfig {

    private final TaskEntityRepositoryJpa taskEntityRepositoryJpa;

    @Bean
    public TaskRepository taskRepositoryJpa(){
        return new TaskRepositoryImpl(taskEntityRepositoryJpa);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(){
        return new CreateTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public FindTaskUseCase findTaskUseCase(){
        return new FindTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(){
        return new CompleteTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public EditTaskUseCase editTaskUseCase(){
        return new EditTaskUseCase(taskRepositoryJpa());
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(){
        return new DeleteTaskUseCase(taskRepositoryJpa());
    }
}
