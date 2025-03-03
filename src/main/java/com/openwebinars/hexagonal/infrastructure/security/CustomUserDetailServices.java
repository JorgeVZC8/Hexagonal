package com.openwebinars.hexagonal.infrastructure.security;

import com.openwebinars.hexagonal.domain.repository.UserRepository;
import com.openwebinars.hexagonal.infrastructure.security.model.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServices implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(username)
                .map(AuthUser::of)
                .orElseThrow(()-> new UsernameNotFoundException("No user with email%s".formatted(username)));
    }
}
