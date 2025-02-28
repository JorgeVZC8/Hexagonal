package com.openwebinars.hexagonal.infrastructure.mapper;

import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.openwebinars.hexagonal.application.usecase.task.edit.EditTaskCommand;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.infrastructure.db.entity.TaskEntity;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskResponse;

import java.time.LocalDateTime;

//Esta clase transformara objetos del modelo de dominio hacia una entindad de la capa de persistencia y viceversa
public class TaskMapper {

    public static TaskEntity toPersistence (Task task){
        TaskEntity t = TaskEntity.builder()
                .id(task.getId() != null ? task.getId().getValue():null) //Hacemos esta comprobacion para evitar un NullPointerException
                .title(task.getTitle())
                .description(task.getDescription())
                .createdAt(LocalDateTime.now())
                .complete(task.isComplete())
                .build();

        return t;
    }

    public static Task toDomain(TaskEntity taskEntity){
        return Task.builder()
                .id(TaskId.of(taskEntity.getId()))
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .createdAt(taskEntity.getCreatedAt())
                .complete(taskEntity.isComplete())
                .build();
    }

    public static CreateTaskCommand toCommand(TaskRequest taskRequest){
        return new CreateTaskCommand(taskRequest.title(), taskRequest.description());
    }

    public static TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId().getValue(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.isComplete()
                );
    }

    public static EditTaskCommand toCommand(Long id, TaskEditRequest taskEditRequest){
        return new EditTaskCommand(
            TaskId.of(id),
            taskEditRequest.title(),
            taskEditRequest.description(),
            taskEditRequest.complete()
        );

    }

}
