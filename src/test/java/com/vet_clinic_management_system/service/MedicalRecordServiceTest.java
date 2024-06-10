package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.*;
import com.vet_clinic_management_system.entity.*;
import com.vet_clinic_management_system.enums.Role;
import com.vet_clinic_management_system.repository.*;
import com.vet_clinic_management_system.service.impl.MedicalRecordServiceImpl;
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

public class MedicalRecordServiceTest {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private MedicalRecordServiceImpl medicalRecordServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void medicalRecordServiceImpl_save() {
        // arrange
        MedicalRecordDTO medicalRecordDTO = MedicalRecordDTO.builder()
                .id(2)
                .diagnosis("Nail trimming")
                .description("Trimmed nails")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(new PetDTO())
                .user(new UserDTO())
                .treatment(new TreatmentDTO())
                .build();

        when(petRepository.findById(any())).thenReturn(new PetEntity());
        when(userRepository.findById(any())).thenReturn(new UserEntity());
        when(treatmentRepository.findById(any())).thenReturn(new TreatmentEntity());

        // act
        medicalRecordServiceImpl.save(medicalRecordDTO);

        // assert
        verify(medicalRecordRepository).save(any());
    }

    @Test
    public void medicalRecordServiceImpl_findById() {
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
        TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .id(2)
                .diagnosis("Nail trimming")
                .description("Trimmed nails")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .treatment(treatmentEntity)
                .build();

        when(medicalRecordRepository.findById(2)).thenReturn(medicalRecordEntity);

        // act
        MedicalRecordDTO medicalRecordDTO = medicalRecordServiceImpl.findById(2);

        // assert
        assertEquals(2, medicalRecordDTO.getId());
        assertEquals(LocalDate.of(2024, 6, 10), medicalRecordDTO.getVisitDate());
        assertEquals("Trimmed nails", medicalRecordDTO.getDescription());
        assertEquals("Maksi", medicalRecordDTO.getPet().getName());

        verify(medicalRecordRepository, times(1)).findById(2);
    }

    @Test
    public void medicalRecordServiceImpl_update() {
        // arrange
        MedicalRecordDTO medicalRecordDTO = MedicalRecordDTO.builder()
                .id(2)
                .diagnosis("Nail trimming")
                .description("Trimmed nails")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(new PetDTO())
                .user(new UserDTO())
                .treatment(new TreatmentDTO())
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
        TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();
        MedicalRecordEntity medicalRecordEntity = MedicalRecordEntity.builder()
                .id(2)
                .diagnosis("Nail trimming")
                .description("Trimmed nails")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .treatment(treatmentEntity)
                .build();

        when(medicalRecordRepository.findById(medicalRecordDTO.getId())).thenReturn(medicalRecordEntity);

        // act
        medicalRecordServiceImpl.update(medicalRecordDTO);

        // assert
        verify(medicalRecordRepository).findById(medicalRecordDTO.getId());
        verify(medicalRecordRepository).update(any(MedicalRecordEntity.class));
    }

    @Test
    public void medicalRecordServiceImpl_findAll() {
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
        TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();

        MedicalRecordEntity medicalRecord1 = MedicalRecordEntity.builder()
                .id(2)
                .diagnosis("Nail trimming")
                .description("Trimmed nails")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .treatment(treatmentEntity)
                .build();
        MedicalRecordEntity medicalRecord2 = MedicalRecordEntity.builder()
                .id(6)
                .diagnosis("Routine Check-up")
                .description("Checked overall health and administered vaccinations")
                .visitDate(LocalDate.of(2024, 6, 10))
                .createdAt(LocalDate.now())
                .pet(petEntity)
                .user(userEntity)
                .treatment(treatmentEntity)
                .build();

        List<MedicalRecordEntity> medicalRecordEntities = Arrays.asList(medicalRecord1, medicalRecord2);

        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordEntities);

        // act
        List<MedicalRecordDTO> medicalRecords = medicalRecordServiceImpl.findAll();

        // assert
        assertEquals(2, medicalRecords.size());
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    public void medicalRecordServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        medicalRecordServiceImpl.delete(id);

        // assert
        verify(medicalRecordRepository, times(1)).delete(id);
    }
}
