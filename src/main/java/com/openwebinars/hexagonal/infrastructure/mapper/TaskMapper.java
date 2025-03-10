package com.openwebinars.hexagonal.infrastructure.mapper;

import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.openwebinars.hexagonal.application.usecase.task.edit.EditTaskCommand;
import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.UserTask;
import com.openwebinars.hexagonal.infrastructure.db.entity.TaskEntity;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.TaskResponse;
import com.openwebinars.hexagonal.infrastructure.web.dto.task.UserTaskResponse;

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
                .author(task.getAuthor() != null ? task.getAuthor().getValue() : null)
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
                .author(UserId.of(taskEntity.getAuthor()))
                .build();
    }

    public static CreateTaskCommand toCommand(TaskRequest taskRequest, UserId author){
        return new CreateTaskCommand(taskRequest.title(), taskRequest.description(), author);
    }

    public static TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId().getValue(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.isComplete(),
                task.getAuthor()
                );
    }

    public static UserTaskResponse toResponse(UserTask userTask){
        return new UserTaskResponse(
                userTask.getTask().getId().getValue(),
                userTask.getTask().getTitle(),
                userTask.getTask().getDescription(),
                userTask.getTask().getCreatedAt(),
                userTask.getTask().isComplete(),
                userTask.getUser() !=null  ? UserMapper.toDto(userTask.getUser()) : null
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
