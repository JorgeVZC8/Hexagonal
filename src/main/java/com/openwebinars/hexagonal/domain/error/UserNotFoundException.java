package com.openwebinars.hexagonal.domain.error;

import com.openwebinars.hexagonal.domain.UserId;

public class UserNotFoundException extends DomainEntityNotFoundException{

    public UserNotFoundException(){
        super("user");
    }

    public UserNotFoundException(UserId id){
        super("user", id.getValue().toString());
    }
}
