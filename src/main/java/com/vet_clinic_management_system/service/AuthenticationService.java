package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserDTO authenticate(CredentialsDTO credentialsDto) {
        UserDTO userDTO = userService.findByUsername(credentialsDto.getLogin());
        String passwordString = new String(credentialsDto.getPassword());
        if (userDTO != null && passwordEncoder.matches(passwordString, userDTO.getPassword())) {
            return userDTO;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public UserDTO findByLogin(String login) {
        UserDTO userDTO = userService.findByUsername(login);
        if (userDTO != null) {
            return UserDTO.builder()
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .phoneNumber(userDTO.getPhoneNumber())
                    .role(userDTO.getRole())
                    .createdAt(userDTO.getCreatedAt())
                    .build();
        }

        throw new RuntimeException("Invalid login");
    }

}
