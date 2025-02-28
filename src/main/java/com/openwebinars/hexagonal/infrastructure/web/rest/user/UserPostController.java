package com.openwebinars.hexagonal.infrastructure.web.rest.user;

import com.openwebinars.hexagonal.application.usecase.user.create.CreateUserUseCase;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.infrastructure.mapper.UserMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserPostController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        User u= createUserUseCase.create(UserMapper.toCommand(userRequest));
        return ResponseEntity.status(201).body(UserMapper.toDto(u));
    }
}
