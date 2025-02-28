package com.openwebinars.hexagonal.domain.repository;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User create(User user);
    User update(User user);
    Optional<User> changePassword(UserId id, String password);
    Optional<User> getUserById(UserId id);
    Optional<User> getUserByEmail(String email);
    List<User> getByIds(Iterable<UserId> ids);

}
