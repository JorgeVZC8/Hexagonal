package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangePasswordUseCase {

    private final UserRepository userRepository;

    public User changePassword(UserId id, String newPassword){
        return userRepository.getUserById(id)
                .map(u->{
                    u.setPassword(newPassword);
                    return userRepository.create(u);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }
}
