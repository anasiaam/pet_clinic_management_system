package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.*;
import com.vet_clinic_management_system.entity.*;

import java.time.LocalDate;

public class MedicalRecordMapper {
    private MedicalRecordMapper() {
        // konstruktor privat
    }

    public static MedicalRecordEntity toEntity(MedicalRecordDTO medicalRecordDTO, PetEntity petEntity, UserEntity userEntity, TreatmentEntity treatmentEntity) {
        MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
        medicalRecordEntity.setCreatedAt(LocalDate.now());
        medicalRecordEntity.setDescription(medicalRecordDTO.getDescription());
        medicalRecordEntity.setVisitDate(medicalRecordDTO.getVisitDate());
        medicalRecordEntity.setDiagnosis(medicalRecordDTO.getDiagnosis());
        medicalRecordEntity.setUser(userEntity);
        medicalRecordEntity.setPet(petEntity);
        medicalRecordEntity.setTreatment(treatmentEntity);
        return medicalRecordEntity;
    }

    public static MedicalRecordEntity toEntityForUpdate(MedicalRecordEntity medicalRecordEntity, MedicalRecordDTO medicalRecordDTO) {
        medicalRecordEntity.setDescription(medicalRecordDTO.getDescription());
        medicalRecordEntity.setVisitDate(medicalRecordDTO.getVisitDate());
        medicalRecordEntity.setDiagnosis(medicalRecordDTO.getDiagnosis());
        return medicalRecordEntity;
    }

    public static MedicalRecordDTO toDTO(MedicalRecordEntity medicalRecordEntity) {
        MedicalRecordDTO medicalRecordDTO = new MedicalRecordDTO();
        medicalRecordDTO.setId(medicalRecordEntity.getId());
        medicalRecordDTO.setDiagnosis(medicalRecordEntity.getDiagnosis());
        medicalRecordDTO.setDescription(medicalRecordEntity.getDescription());
        medicalRecordDTO.setVisitDate(medicalRecordEntity.getVisitDate());
        medicalRecordDTO.setCreatedAt(medicalRecordEntity.getCreatedAt());
        medicalRecordDTO.setPet(PetMapper.toDTO(medicalRecordEntity.getPet()));
        medicalRecordDTO.setUser(UserMapper.toDTO(medicalRecordEntity.getUser()));
        medicalRecordDTO.setTreatment(TreatmentMapper.toDTO(medicalRecordEntity.getTreatment()));
        return medicalRecordDTO;
    }
}
