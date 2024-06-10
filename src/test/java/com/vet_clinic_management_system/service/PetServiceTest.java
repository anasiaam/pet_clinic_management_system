package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.DTO.PetOwnerDTO;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.PetOwnerEntity;
import com.vet_clinic_management_system.repository.PetOwnerRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.service.impl.PetServiceImpl;
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

public class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @Mock
    private PetOwnerRepository petOwnerRepository;

    @InjectMocks
    private PetServiceImpl petServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void petServiceImpl_save() {
        // arrange
        PetDTO petDTO = PetDTO.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(new PetOwnerDTO())
                .build();

        when(petOwnerRepository.findById(any())).thenReturn(new PetOwnerEntity());

        // act
        petServiceImpl.save(petDTO);

        // assert
        verify(petRepository).save(any());
    }

    @Test
    public void petServiceImpl_findById() {
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
                .id(6)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();

        when(petRepository.findById(6)).thenReturn(petEntity);

        // act
        PetDTO petDTO = petServiceImpl.findById(6);

        // assert
        assertEquals(6, petDTO.getId());
        assertEquals(LocalDate.now(), petDTO.getCreatedAt());

        verify(petRepository, times(1)).findById(6);
    }

    @Test
    public void petServiceImpl_update() {
        // arrange
        PetDTO petDTO = PetDTO.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(new PetOwnerDTO())
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

        when(petRepository.findById(petDTO.getId())).thenReturn(petEntity);

        // act
        petServiceImpl.update(petDTO);

        // assert
        verify(petRepository).findById(petDTO.getId());
        verify(petRepository).update(any(PetEntity.class));
    }

    @Test
    public void petServiceImpl_findAll() {
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
        PetEntity petEntity1 = PetEntity.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();
        PetEntity petEntity2 = PetEntity.builder()
                .id(1)
                .name("Maksi")
                .species("dog")
                .breed("Alaskan Malamute")
                .age(7)
                .createdAt(LocalDate.now())
                .petOwner(petOwnerEntity)
                .build();

        List<PetEntity> petEntities = Arrays.asList(petEntity1, petEntity2);

        when(petRepository.findAll()).thenReturn(petEntities);

        // act
        List<PetDTO> pets = petServiceImpl.findAll();

        // assert
        assertEquals(2, pets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    public void petServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        petServiceImpl.delete(id);

        // assert
        verify(petRepository, times(1)).delete(id);
    }
}
