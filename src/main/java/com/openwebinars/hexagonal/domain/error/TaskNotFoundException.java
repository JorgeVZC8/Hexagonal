package com.openwebinars.hexagonal.domain.error;

import com.openwebinars.hexagonal.domain.Task;
import com.openwebinars.hexagonal.domain.TaskId;

public class TaskNotFoundException extends DomainEntityNotFoundException{

    public TaskNotFoundException() {
        super("task");
    }

    public TaskNotFoundException(TaskId id) {
        super("task", id.getValue().toString());
    }
}
