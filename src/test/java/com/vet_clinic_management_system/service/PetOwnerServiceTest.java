package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.*;
import com.vet_clinic_management_system.entity.*;
import com.vet_clinic_management_system.repository.*;
import com.vet_clinic_management_system.service.impl.PetOwnerServiceImpl;
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

public class PetOwnerServiceTest {
    @Mock
    private PetOwnerRepository petOwnerRepository;

    @InjectMocks
    private PetOwnerServiceImpl petOwnerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void petOwnerServiceImpl_save() {
        // arrange
        PetOwnerDTO petOwnerDTO = PetOwnerDTO.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();

        // act
        petOwnerService.save(petOwnerDTO);

        // assert
        verify(petOwnerRepository).save(any());
    }

    @Test
    public void petOwnerServiceImpl_findById() {
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

        when(petOwnerRepository.findById(6)).thenReturn(petOwnerEntity);

        // act
        PetOwnerDTO petOwnerDTO = petOwnerService.findById(6);

        // assert
        assertEquals(6, petOwnerDTO.getId());
        assertEquals(LocalDate.now(), petOwnerDTO.getCreatedAt());

        verify(petOwnerRepository, times(1)).findById(6);
    }

    @Test
    public void petOwnerServiceImpl_update() {
        // arrange
        PetOwnerDTO petOwnerDTO = PetOwnerDTO.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
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

        when(petOwnerRepository.findById(petOwnerDTO.getId())).thenReturn(petOwnerEntity);

        // act
        petOwnerService.update(petOwnerDTO);

        // assert
        verify(petOwnerRepository).findById(petOwnerDTO.getId());
        verify(petOwnerRepository).update(any(PetOwnerEntity.class));
    }

    @Test
    public void petOwnerServiceImpl_findAll() {
        // arrange
        PetOwnerEntity petOwnerEntity1 = PetOwnerEntity.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();
        PetOwnerEntity petOwnerEntity2 = PetOwnerEntity.builder()
                .id(6)
                .firstName("Ppp")
                .lastName("Lll")
                .phoneNumber("12345678")
                .email("ppp_lll@gmail.com")
                .address("xxxyyy")
                .createdAt(LocalDate.now())
                .build();

        List<PetOwnerEntity> petOwnerEntities = Arrays.asList(petOwnerEntity1, petOwnerEntity2);

        when(petOwnerRepository.findAll()).thenReturn(petOwnerEntities);

        // act
        List<PetOwnerDTO> petOwners = petOwnerService.findAll();

        // assert
        assertEquals(2, petOwners.size());
        verify(petOwnerRepository, times(1)).findAll();
    }

    @Test
    public void petOwnerServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        petOwnerService.delete(id);

        // assert
        verify(petOwnerRepository, times(1)).delete(id);
    }
}
