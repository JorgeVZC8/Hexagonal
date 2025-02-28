package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditUserUseCase {

    private final UserRepository userRepository;

    public User update(EditUserCommand command){
        return userRepository.getUserById(command.id())
                .map(u->{
                    u.setName(command.name());
                    u.setEmail(command.email());
                    u.setPassword(command.password());
                    return userRepository.create(u);
                }).orElseThrow(()-> new UserNotFoundException(command.id()));
    }
}
