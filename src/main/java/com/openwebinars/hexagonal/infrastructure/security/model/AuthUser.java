package com.openwebinars.hexagonal.infrastructure.security.model;

import com.openwebinars.hexagonal.domain.User;
import com.openwebinars.hexagonal.domain.UserId;
import com.openwebinars.hexagonal.infrastructure.db.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUser implements UserDetails {

    private UUID id;
    private String nombre, email, password;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UserId getIdAsUserId(){
        return UserId.of(id);
    }

    public static AuthUser of(User user){
        return AuthUser.builder()
                .id(user.getId().getValue())
                .nombre(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(UserRole.of(user.getRole()))
                .build();
    }

}
