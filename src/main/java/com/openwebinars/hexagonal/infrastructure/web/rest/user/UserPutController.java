package com.openwebinars.hexagonal.infrastructure.web.rest.user;

import com.openwebinars.hexagonal.application.usecase.user.edit.ChangePasswordUseCase;
import com.openwebinars.hexagonal.application.usecase.user.edit.EditUserUseCase;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.mapper.UserMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class UserPutController {

    private final EditUserUseCase editUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @PutMapping("{id}")
    public UserResponse editUser(@PathVariable UUID id, @RequestBody UserEditRequest userEditRequest){
        User u= editUserUseCase.update(UserMapper.toCommand(id, userEditRequest));
        return UserMapper.toResponse(u);
    }

    @PutMapping("/{id}/change-password")
    public UserResponse changePassword(@PathVariable UserId id, String newPassword){
        User u= changePasswordUseCase.changePassword(id, newPassword);
        return UserMapper.toResponse(u);
    }


}
