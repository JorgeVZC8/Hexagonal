package com.openwebinars.hexagonal.application.usecase.user.create;

import com.openwebinars.hexagonal.application.usecase.task.create.CreateTaskCommand;
import com.openwebinars.hexagonal.application.validation.EmailAlreadyExistsException;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public User create(CreateUserCommand command){

        requiredUniqueEmail(command.email());

        User u =User.builder()
                .name(command.name())
                .email(command.email())
                .password(command.Password())
                .role(command.role().toString())
                .build();

        return userRepository.create(u);
    }

    private void requiredUniqueEmail(String email) {
        userRepository.getUserByEmail(email)
                .ifPresent(e->{ throw new EmailAlreadyExistsException();});
    }
}
