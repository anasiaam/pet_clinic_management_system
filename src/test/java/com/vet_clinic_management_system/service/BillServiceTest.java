package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.BillDTO;
import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.entity.*;
import com.vet_clinic_management_system.repository.BillRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.service.impl.BillServiceImpl;
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
import static org.mockito.Mockito.times;

public class BillServiceTest {
    @Mock
    private BillRepository billRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private BillServiceImpl billServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void billServiceImpl_save() {
        // arrange
        BillDTO billDTO = BillDTO.builder()
                .id(2)
                .amount(234)
                .pet(new PetDTO())
                .billingDate(LocalDate.now())
                .build();

        when(petRepository.findById(any())).thenReturn(new PetEntity());

        // act
        billServiceImpl.save(billDTO);

        // assert
        verify(billRepository).save(any());
    }

    @Test
    public void billServiceImpl_findById() {
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
        BillEntity billEntity = BillEntity.builder()
                .id(1)
                .amount(234)
                .pet(petEntity)
                .billingDate(LocalDate.now())
                .build();

        when(billRepository.findById(1)).thenReturn(billEntity);

        // act
        BillDTO billDTO = billServiceImpl.findById(1);

        // assert
        assertEquals(1, billDTO.getId());
        assertEquals(LocalDate.of(2024, 6, 10), billDTO.getBillingDate());
        assertEquals(234, billDTO.getAmount());
        assertEquals("Maksi", billDTO.getPet().getName());

        verify(billRepository, times(1)).findById(1);
    }

    @Test
    public void billServiceImpl_update() {
        // arrange
        BillDTO billDTO = BillDTO.builder()
                .id(2)
                .amount(234)
                .pet(new PetDTO())
                .billingDate(LocalDate.now())
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
        BillEntity billEntity = BillEntity.builder()
                .id(2)
                .amount(234)
                .pet(petEntity)
                .billingDate(LocalDate.now())
                .build();
        when(billRepository.findById(billDTO.getId())).thenReturn(billEntity);

        // act
        billServiceImpl.update(billDTO);

        // assert
        verify(billRepository).findById(billDTO.getId());
        verify(billRepository).update(any(BillEntity.class));
    }

    @Test
    public void billServiceImpl_findAll() {
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

        BillEntity billEntity1 = BillEntity.builder()
                .id(2)
                .amount(234)
                .pet(petEntity)
                .billingDate(LocalDate.now())
                .build();
        BillEntity billEntity2 = BillEntity.builder()
                .id(6)
                .amount(4)
                .pet(petEntity)
                .billingDate(LocalDate.now())
                .build();

        List<BillEntity> billEntities = Arrays.asList(billEntity1, billEntity2);

        when(billRepository.findAll()).thenReturn(billEntities);

        // act
        List<BillDTO> appointments = billServiceImpl.findAll();

        // assert
        assertEquals(2, appointments.size());
        verify(billRepository, times(1)).findAll();
    }

    @Test
    public void billServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        billServiceImpl.delete(id);

        // assert
        verify(billRepository, times(1)).delete(id);
    }
}
