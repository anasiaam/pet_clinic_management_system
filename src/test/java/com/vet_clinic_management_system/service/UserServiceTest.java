package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.enums.Role;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userServiceImpl_save() {
        // arrange
        UserDTO userDTO = UserDTO.builder()
                .id(4)
                .username("doktor123")
                .password("abc")
                .email("doktor123@gmail.com")
                .firstName("Abc")
                .lastName("Cba")
                .phoneNumber("12345612")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();

        // act
        userService.save(userDTO);

        // assert
        verify(userRepository).save(any());
    }

    @Test
    public void userServiceImpl_findById() {
        // arrange
        UserEntity userEntity = UserEntity.builder()
                .id(4)
                .username("doktor123")
                .password("abc")
                .email("doktor123@gmail.com")
                .firstName("Abc")
                .lastName("Cba")
                .phoneNumber("12345612")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();

        when(userRepository.findById(4)).thenReturn(userEntity);

        // act
        UserDTO userDTO = userService.findById(4);

        // assert
        assertEquals(4, userDTO.getId());
        assertEquals(LocalDate.now(), userDTO.getCreatedAt());

        verify(userRepository, times(1)).findById(4);
    }

    @Test
    public void userServiceImpl_update() {
        // arrange
        UserDTO userDTO = UserDTO.builder()
                .id(4)
                .username("doktor123")
                .password("abc")
                .email("doktor123@gmail.com")
                .firstName("Abc")
                .lastName("Cba")
                .phoneNumber("12345612")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();
        UserEntity userEntity = UserEntity.builder()
                .id(9)
                .username("receptionist123")
                .password("xyz")
                .email("receptionist123@gmail.com")
                .firstName("Xyz")
                .lastName("Zyx")
                .phoneNumber("12345612")
                .role(Role.RECEPTIONIST)
                .createdAt(LocalDate.now())
                .build();

        when(userRepository.findById(userDTO.getId())).thenReturn(userEntity);

        // act
        userService.update(userDTO);

        // assert
        verify(userRepository).findById(userDTO.getId());
        verify(userRepository).update(any(UserEntity.class));
    }

    @Test
    public void userServiceImpl_findAll() {
        // arrange
        UserEntity userEntity1 = UserEntity.builder()
                .id(9)
                .username("receptionist123")
                .password("xyz")
                .email("receptionist123@gmail.com")
                .firstName("Xyz")
                .lastName("Zyx")
                .phoneNumber("12345612")
                .role(Role.RECEPTIONIST)
                .createdAt(LocalDate.now())
                .build();
        UserEntity userEntity2 = UserEntity.builder()
                .id(4)
                .username("doktor123")
                .password("abc")
                .email("doktor123@gmail.com")
                .firstName("Abc")
                .lastName("Cba")
                .phoneNumber("12345612")
                .role(Role.DOCTOR)
                .createdAt(LocalDate.now())
                .build();

        List<UserEntity> userEntities = Arrays.asList(userEntity1, userEntity2);

        when(userRepository.findAll()).thenReturn(userEntities);

        // act
        List<UserDTO> users = userService.findAll();

        // assert
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void userServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        userService.delete(id);

        // assert
        verify(userRepository, times(1)).delete(id);
    }
}
