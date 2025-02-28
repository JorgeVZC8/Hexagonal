package com.openwebinars.hexagonal.infrastructure.web.rest.user;

import com.openwebinars.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.mapper.TaskMapper;
import com.openwebinars.hexagonal.infrastructure.mapper.UserMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class UserGetController {

    private final FindUserUseCase findUserUseCase;

    @GetMapping
    public List<UserResponse> allUsers(@RequestParam List<UUID> uuids) {
        return findUserUseCase.getByIds(
                        uuids.stream().map(UserId::of).toList()
                )
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }


    @GetMapping("/{id}")
    public UserResponse userById(@PathVariable UUID id){
        User u= findUserUseCase.findById(UserId.of(id));
        return UserMapper.toResponse(u);
    }
}
