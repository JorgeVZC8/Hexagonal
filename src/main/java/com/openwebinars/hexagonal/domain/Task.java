package com.openwebinars.hexagonal.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class Task {

    @Setter(AccessLevel.NONE) //Evita que se pueda modificar la identidad de una tarea una vez haya sido creada
    private TaskId id;
    private String title, description;
    private LocalDateTime createdAt;
    private boolean complete;
    private UserId author;

    public void taskComplete(){
        this.complete=true;
    }
}
