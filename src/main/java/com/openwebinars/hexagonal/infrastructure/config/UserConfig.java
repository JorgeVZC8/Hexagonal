package com.openwebinars.hexagonal.infrastructure.config;

import com.openwebinars.hexagonal.application.usecase.user.create.CreateUserUseCase;
import com.openwebinars.hexagonal.application.usecase.user.edit.ChangePasswordUseCase;
import com.openwebinars.hexagonal.application.usecase.user.edit.EditUserUseCase;
import com.openwebinars.hexagonal.application.usecase.user.find.FindUserUseCase;
import com.openwebinars.hexagonal.domain.repository.UserRepository;
import com.openwebinars.hexagonal.infrastructure.db.repos.impl.UserRepositoryImpl;
import com.openwebinars.hexagonal.infrastructure.db.repos.jpa.UserEntityRapositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final UserEntityRapositoryJpa userEntityRapositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl(userEntityRapositoryJpa, passwordEncoder);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(){
        return new CreateUserUseCase(userRepository());
    }

    @Bean
    public FindUserUseCase findUserUseCase(){
        return new FindUserUseCase(userRepository());
    }

    @Bean
    public EditUserUseCase editUserUseCase(){return new EditUserUseCase(userRepository());}

    @Bean
    public ChangePasswordUseCase changePasswordUseCase(){return new ChangePasswordUseCase(userRepository(),passwordEncoder);}

}
