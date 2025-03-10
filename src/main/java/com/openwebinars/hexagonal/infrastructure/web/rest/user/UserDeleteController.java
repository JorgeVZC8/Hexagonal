package com.openwebinars.hexagonal.infrastructure.web.rest.user;

import com.openwebinars.hexagonal.application.usecase.user.delete.DeleteUserUseCase;
import com.openwebinars.hexagonal.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth/register")
@RequiredArgsConstructor
public class UserDeleteController {

    private final DeleteUserUseCase deleteUserUseCase;

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteUser (@PathVariable UUID id){
        deleteUserUseCase.deleteById(UserId.of(id));
        return ResponseEntity.noContent().build();
    }
}
