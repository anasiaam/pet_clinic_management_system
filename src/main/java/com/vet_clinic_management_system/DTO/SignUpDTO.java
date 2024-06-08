package com.vet_clinic_management_system.DTO;

import com.vet_clinic_management_system.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String username;
    private char[] password;
    private Role role;
    private String email;
    private String phoneNumber;
}
