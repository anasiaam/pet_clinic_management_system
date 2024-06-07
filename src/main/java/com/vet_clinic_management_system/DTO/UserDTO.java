package com.vet_clinic_management_system.DTO;

import com.vet_clinic_management_system.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements UserDetails {
    private Integer id;
    @NotNull(message = "{validation.entity.users.username}")
    private String username;
    @NotNull(message = "{validation.entity.users.password}")
    private String password;
    @NotNull(message = "{validation.entity.users.email}")
    private String email;
    @NotNull(message = "{validation.entity.users.firstName}")
    private String firstName;
    @NotNull(message = "{validation.entity.users.lastName}")
    private String lastName;
    @NotNull(message = "{validation.entity.users.phoneNumber}")
    private String phoneNumber;
    @NotNull(message = "{validation.entity.users.role}")
    private Role role;
    private LocalDate createdAt;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
