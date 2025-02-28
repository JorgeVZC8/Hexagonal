package com.openwebinars.hexagonal.application.usecase.user.find;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.domain.error.UserNotFoundException;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FindUserUseCase {

    private final UserRepository userRepository;

    public User findById(UserId id){
        return userRepository.getUserById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    public List<User> getByIds(Iterable<UserId> ids){
        List<User> result = userRepository.getByIds(ids);
        if (result.isEmpty()){
            throw new UserNotFoundException();
        }
        return result;
    }

    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
