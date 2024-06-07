package com.vet_clinic_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;
}
