package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.TreatmentDTO;
import com.vet_clinic_management_system.entity.TreatmentEntity;
import com.vet_clinic_management_system.repository.TreatmentRepository;
import com.vet_clinic_management_system.service.impl.TreatmentServiceImpl;
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

public class TreatmentServiceTest {
    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private TreatmentServiceImpl treatmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void treatmentServiceImpl_save() {
        // arrange
        TreatmentDTO treatmentDTO = TreatmentDTO.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();

        // act
        treatmentService.save(treatmentDTO);

        // assert
        verify(treatmentRepository).save(any());
    }

    @Test
    public void treatmentServiceImpl_findById() {
        // arrange
        TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(6)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();

        when(treatmentRepository.findById(6)).thenReturn(treatmentEntity);

        // act
        TreatmentDTO treatmentDTO = treatmentService.findById(6);

        // assert
        assertEquals(6, treatmentDTO.getId());
        assertEquals(LocalDate.now(), treatmentDTO.getCreatedAt());

        verify(treatmentRepository, times(1)).findById(6);
    }

    @Test
    public void treatmentServiceImpl_update() {
        // arrange
        TreatmentDTO treatmentDTO = TreatmentDTO.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();
        TreatmentEntity treatmentEntity = TreatmentEntity.builder()
                .id(5)
                .medication("Eye Drops")
                .description("Treatment for eye infections")
                .createdAt(LocalDate.now())
                .build();

        when(treatmentRepository.findById(treatmentDTO.getId())).thenReturn(treatmentEntity);

        // act
        treatmentService.update(treatmentDTO);

        // assert
        verify(treatmentRepository).findById(treatmentDTO.getId());
        verify(treatmentRepository).update(any(TreatmentEntity.class));
    }

    @Test
    public void treatmentServiceImpl_findAll() {
        // arrange
        TreatmentEntity treatmentEntity1 = TreatmentEntity.builder()
                .id(5)
                .medication("Eye Drops")
                .description("Treatment for eye infections")
                .createdAt(LocalDate.now())
                .build();
        TreatmentEntity treatmentEntity2 = TreatmentEntity.builder()
                .id(2)
                .medication("Ear Drops")
                .description("Treatment for ear infections")
                .createdAt(LocalDate.now())
                .build();

        List<TreatmentEntity> treatmentEntities = Arrays.asList(treatmentEntity1, treatmentEntity2);

        when(treatmentRepository.findAll()).thenReturn(treatmentEntities);

        // act
        List<TreatmentDTO> pets = treatmentService.findAll();

        // assert
        assertEquals(2, pets.size());
        verify(treatmentRepository, times(1)).findAll();
    }

    @Test
    public void treatmentServiceImpl_delete() {
        // arrange
        Integer id = 1;

        // act
        treatmentService.delete(id);

        // assert
        verify(treatmentRepository, times(1)).delete(id);
    }
}
