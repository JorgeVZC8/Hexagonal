package com.openwebinars.hexagonal.application.usecase.user.delete;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    public void delete(User user) {
        deleteById(user.getId());
    }

    public void deleteById(UserId id) {
        userRepository.delete(id);
    }
}
