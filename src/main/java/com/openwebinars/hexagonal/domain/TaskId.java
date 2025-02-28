package com.openwebinars.hexagonal.domain;

import lombok.Value;

//Encapsulamos la identidad de la entidad de dominio
@Value
public class TaskId {
    Long value;

    public static TaskId of(Long value){
        return new TaskId(value);
    }
}
