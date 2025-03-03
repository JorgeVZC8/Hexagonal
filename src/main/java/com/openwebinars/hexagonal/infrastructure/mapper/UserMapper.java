package com.openwebinars.hexagonal.infrastructure.mapper;

import com.openwebinars.hexagonal.application.usecase.user.create.CreateUserCommand;
import com.openwebinars.hexagonal.application.usecase.user.edit.ChangePasswordCommand;
import com.openwebinars.hexagonal.application.usecase.user.edit.EditUserCommand;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserEntity;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.ChangePasswordRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserResponse;

import java.util.UUID;

public class UserMapper {

    public static UserEntity toPersistence(User user){
        UserEntity u= UserEntity.builder()
                .id(user.getId() != null ? user.getId().getValue():null)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(UserRole.of(user.getRole()))
                .build();

        return u;
    }

    public static User toDomain(UserEntity user){
        User u= User.builder()
                .id(UserId.of(user.getId()))
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().toString())
                .build();

        return u;
    }

    public static UserResponse toDto(User u){
        return new UserResponse(u.getId().getValue(), u.getName(), u.getEmail(), u.getRole());
    }

    public static CreateUserCommand toCommand(UserRequest u){
        return new CreateUserCommand(u.name(), u.email(),u.password());
    }

    public static UserResponse toResponse(User u){
        return new UserResponse(
                u.getId().getValue(),
                u.getName(),
                u.getEmail(),
                u.getRole()

        );
    }

    public static EditUserCommand toCommand(UUID id, UserEditRequest userEditRequest) {
        return new EditUserCommand(
                UserId.of(id),
                userEditRequest.name(),
                userEditRequest.email(),
                userEditRequest.role()
        );
    }

    public static ChangePasswordCommand toCommand(UUID id, ChangePasswordRequest passwordRequest) {
        return new ChangePasswordCommand(
                UserId.of(id),
                passwordRequest.newPassword()
        );
    }
}
