package com.openwebinars.hexagonal.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class UserId {
    UUID value;

    public static UserId of(UUID value){
        return new UserId(value);
    }
}
