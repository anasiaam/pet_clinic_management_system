package com.vet_clinic_management_system.DTO;

import com.vet_clinic_management_system.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    public Integer id;
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
}
