package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.MedicalRecordEntity;

import java.util.List;

public interface MedicalRecordRepository {
    void save(MedicalRecordEntity medicalRecordEntity);
    MedicalRecordEntity findById(Integer id);
    void update(MedicalRecordEntity medicalRecordEntity);
    List<MedicalRecordEntity> findAll();
    void delete(Integer id);
}
