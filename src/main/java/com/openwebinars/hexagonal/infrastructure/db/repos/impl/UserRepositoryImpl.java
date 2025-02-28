package com.openwebinars.hexagonal.infrastructure.db.repos.impl;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserEntity;
import com.openwebinars.hexagonal.infrastructure.db.repos.jpa.UserEntityRapositoryJpa;
import com.openwebinars.hexagonal.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserEntityRapositoryJpa userEntityRapositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {

        UserEntity entity= UserMapper.toPersistence(user);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return UserMapper.toDomain(userEntityRapositoryJpa.save(entity));
    }

    @Override
    public User update(User user) {
        UserEntity entity= UserMapper.toPersistence(user);
        return UserMapper.toDomain(userEntityRapositoryJpa.save(entity));
    }

    @Override
    public Optional<User> changePassword(UserId id, String newPassword) {

        Optional<UserEntity> entity= userEntityRapositoryJpa.findById(id.getValue())
                .map(u->{
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return userEntityRapositoryJpa.save(u);
                });
        return entity.map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> getUserById(UserId id) {
        return userEntityRapositoryJpa.findById(id.getValue())
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userEntityRapositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public List<User> getByIds(Iterable<UserId> ids) {
        List<UUID> uuids=
                StreamSupport.stream(ids.spliterator(),false)
                        .map(UserId::getValue)
                        .toList();
        return userEntityRapositoryJpa.findAllById(uuids)
                .stream()
                .map(UserMapper::toDomain)
                .toList();
    }
}
