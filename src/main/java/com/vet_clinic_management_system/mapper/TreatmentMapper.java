package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.TreatmentDTO;
import com.vet_clinic_management_system.entity.TreatmentEntity;

import java.time.LocalDate;

public class TreatmentMapper {
    private TreatmentMapper() {
        // konstruktor privat
    }

    public static TreatmentEntity toEntity(TreatmentDTO treatmentDTO) {
        TreatmentEntity treatmentEntity = new TreatmentEntity();
        treatmentEntity.setMedication(treatmentDTO.getMedication());
        treatmentEntity.setDescription(treatmentDTO.getDescription());
        treatmentEntity.setCreatedAt(LocalDate.now());
        return treatmentEntity;
    }

    public static TreatmentEntity toEntityForUpdate(TreatmentEntity treatmentEntity, TreatmentDTO treatmentDTO) {
        treatmentEntity.setMedication(treatmentDTO.getMedication());
        treatmentEntity.setDescription(treatmentDTO.getDescription());
        return treatmentEntity;
    }

    public static TreatmentDTO toDTO(TreatmentEntity treatmentEntity) {
        TreatmentDTO treatmentDTO = new TreatmentDTO();
        treatmentDTO.setId(treatmentEntity.getId());
        treatmentDTO.setMedication(treatmentEntity.getMedication());
        treatmentDTO.setDescription(treatmentEntity.getDescription());
        treatmentDTO.setCreatedAt(treatmentEntity.getCreatedAt());
        return treatmentDTO;
    }
}
