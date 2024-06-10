package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.enums.Role;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void authenticationService_authenticate() {
        // arrange
        String username = "testUser";
        String password = "testPassword";
        String encodedPassword = "encodedTestPassword";

        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username(username)
                .password(encodedPassword)
                .email("testdoctor@example.com")
                .firstName("Test")
                .lastName("Doctor")
                .phoneNumber("123456789")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setUsername(username);
        credentialsDTO.setPassword(password.toCharArray());

        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        // act
        UserDTO result = authenticationService.authenticate(credentialsDTO);

        // assert
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }

    @Test
    public void authenticationService_findByLogin() {
        // arrange
        String username = "testUser";

        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .username(username)
                .password("password")
                .email("testdoctor@example.com")
                .firstName("Test")
                .lastName("Doctor")
                .phoneNumber("123456789")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();

        when(userRepository.findByUsername(username)).thenReturn(userEntity);

        // act
        UserDTO result = authenticationService.findByLogin(username);

        // assert
        assertEquals(username, result.getUsername());
        assertEquals(userEntity.getEmail(), result.getEmail());
        assertEquals(userEntity.getFirstName(), result.getFirstName());
        assertEquals(userEntity.getLastName(), result.getLastName());
        assertEquals(userEntity.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(userEntity.getRole(), result.getRole());
        assertEquals(userEntity.getCreatedAt(), result.getCreatedAt());

        verify(userRepository, times(1)).findByUsername(username);
    }
}
