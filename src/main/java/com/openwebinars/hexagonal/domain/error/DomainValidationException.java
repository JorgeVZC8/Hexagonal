package com.openwebinars.hexagonal.domain.error;

public class DomainValidationException extends RuntimeException{

    public DomainValidationException(String message){
        super(message);
    }
}
