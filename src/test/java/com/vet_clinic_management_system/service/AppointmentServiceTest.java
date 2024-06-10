package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.AppointmentDTO;
import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.AppointmentEntity;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.PetOwnerEntity;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.enums.Role;
import com.vet_clinic_management_system.repository.AppointmentRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.impl.AppointmentServiceImpl;
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

public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void appointmentServiceImpl_save() {
        // arrange
        // krijojme nje AppointmentDTO fake
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .appointmentDate(LocalDate.of(2024, 6, 8))
                .pet(new PetDTO())
                .user(new UserDTO())
                .build();

        when(petRepository.findById(any())).thenReturn(new PetEntity());
        when(userRepository.findById(any())).thenReturn(new UserEntity());

        // act
        appointmentServiceImpl.save(appointmentDTO);

        // assert
        verify(appointmentRepository).save(any());
    }

    @Test
    public void appointmentServiceImpl_findById() {
        // arrange
        PetOwnerEntity petOwnerEntity = PetOwnerEntity.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();
        PetEntity petEntity = PetEntity.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();
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
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2024, 6, 8))
                .reason("tummy ache")
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .build();

        when(appointmentRepository.findById(1)).thenReturn(appointmentEntity);

        // act
        AppointmentDTO appointmentDTO = appointmentServiceImpl.findById(1);

        // assert
        assertEquals(1, appointmentDTO.getId());
        assertEquals(LocalDate.of(2024, 6, 8), appointmentDTO.getAppointmentDate());
        assertEquals("tummy ache", appointmentDTO.getReason());
        assertEquals("Maksi", appointmentDTO.getPet().getName());
        assertEquals("doktor123", appointmentDTO.getUser().getUsername());

        verify(appointmentRepository, times(1)).findById(1);
    }

    @Test
    public void appointmentServiceImpl_update() {
        // arrange
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2024, 6, 8))
                .pet(new PetDTO())
                .user(new UserDTO())
                .build();
        PetOwnerEntity petOwnerEntity = PetOwnerEntity.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();
        PetEntity petEntity = PetEntity.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();
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
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2024, 6, 8))
                .reason("tummy ache")
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .build();
        when(appointmentRepository.findById(appointmentDTO.getId())).thenReturn(appointmentEntity);

        // act
        appointmentServiceImpl.update(appointmentDTO);

        // assert
        verify(appointmentRepository).findById(appointmentDTO.getId());
        verify(appointmentRepository).update(any(AppointmentEntity.class));
    }

    @Test
    public void appointmentServiceImpl_findAll() {
        // arrange
        PetOwnerEntity petOwnerEntity = PetOwnerEntity.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();
        PetEntity petEntity = PetEntity.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();
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

        AppointmentEntity appointmentEntity1 = AppointmentEntity.builder()
                .id(1)
                .appointmentDate(LocalDate.of(2024, 6, 8))
                .reason("tummy ache")
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .build();
        AppointmentEntity appointmentEntity2 = AppointmentEntity.builder()
                .id(2)
                .appointmentDate(LocalDate.of(2024, 7, 8))
                .reason("check-up")
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .build();

        List<AppointmentEntity> appointmentEntities = Arrays.asList(appointmentEntity1, appointmentEntity2);

        when(appointmentRepository.findAll()).thenReturn(appointmentEntities);

        // act
        List<AppointmentDTO> appointments = appointmentServiceImpl.findAll();

        // assert
        assertEquals(2, appointments.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    public void appointmentServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        appointmentServiceImpl.delete(id);

        // assert
        verify(appointmentRepository, times(1)).delete(id);
    }
}
