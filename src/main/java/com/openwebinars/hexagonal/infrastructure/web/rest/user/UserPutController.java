package com.openwebinars.hexagonal.infrastructure.web.rest.user;

import com.openwebinars.hexagonal.application.usecase.user.edit.ChangePasswordCommand;
import com.openwebinars.hexagonal.application.usecase.user.edit.ChangePasswordUseCase;
import com.openwebinars.hexagonal.application.usecase.user.edit.EditUserUseCase;
import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.infrastructure.mapper.UserMapper;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.ChangePasswordRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserEditRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserRequest;
import com.openwebinars.hexagonal.infrastructure.web.dto.user.UserResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class UserPutController {

    private final EditUserUseCase editUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserEditRequest userEditRequest) {

        try {
            User u = editUserUseCase.update(UserMapper.toCommand(id, userEditRequest));
            return ResponseEntity.ok(UserMapper.toResponse(u));

        } catch (ConstraintViolationException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request. Se puede devolver null o un objeto de error más descriptivo
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request. Se puede devolver null o un objeto de error más descriptivo
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500 Internal Server Error
        }
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable UUID id,
            @Valid @RequestBody ChangePasswordRequest request) {

        try {
            ChangePasswordCommand command = UserMapper.toCommand(id,request);
            changePasswordUseCase.changePassword(command);
            return ResponseEntity.ok().build();

        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
