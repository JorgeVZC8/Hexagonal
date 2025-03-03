package com.openwebinars.hexagonal.application.usecase.user.edit;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void changePassword(ChangePasswordCommand changePasswordCommand){
        User u= userRepository.getUserById(changePasswordCommand.id())
                .orElseThrow(()-> new UserNotFoundException(changePasswordCommand.id()));

        String encodePassword = passwordEncoder.encode(changePasswordCommand.newPassword());
        u.setPassword(encodePassword);
        userRepository.create(u);
    }
}
