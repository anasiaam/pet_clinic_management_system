package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.mapper.UserMapper;
import com.vet_clinic_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO authenticate(CredentialsDTO credentialsDto) {
        UserDTO userDTO = UserMapper.toDTO(userRepository.findByUsername(credentialsDto.getUsername()));
        String passwordString = new String(credentialsDto.getPassword());
        if (passwordEncoder.matches(passwordString, userDTO.getPassword())) {
            return userDTO;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public UserDTO findByLogin(String login) {
        UserDTO userDTO = UserMapper.toDTO(userRepository.findByUsername(login));
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

}
