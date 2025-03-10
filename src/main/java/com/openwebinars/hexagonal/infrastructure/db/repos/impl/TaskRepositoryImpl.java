package com.openwebinars.hexagonal.infrastructure.db.repos.impl;


import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.repository.TaskRepository;
import com.openwebinars.hexagonal.infrastructure.db.entity.TaskEntity;
import com.openwebinars.hexagonal.infrastructure.db.repos.jpa.TaskEntityRepositoryJpa;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskEntityRepositoryJpa taskEntityRepositoryJpa;

    @Override
    public Task save(Task task) {
        TaskEntity entity= TaskMapper.toPersistence(task); //Mapeamos la entidad de dominio para trasformarla en un taskentity y guardarlo en el repositorio.
        return TaskMapper.toDomain(taskEntityRepositoryJpa.save(entity)); //Una vez guardado en bbdd, transformamos de nuevo en un dominio.
    }

    @Override
    public List<Task> getAll() {
        return taskEntityRepositoryJpa.findAll()
                .stream()
                .map(TaskMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Task> getById(TaskId id) {
        return taskEntityRepositoryJpa.findById(id.getValue())
                .map(TaskMapper::toDomain);
    }

    @Override
    public void delete(TaskId id) {
       taskEntityRepositoryJpa.deleteById(id.getValue());
    }

    @Override
    public List<Task> getByUserId(UserId id) {
        return taskEntityRepositoryJpa.findByAuthor(id.getValue())
                .stream()
                .map(TaskMapper::toDomain)
                .toList();
    }
}
