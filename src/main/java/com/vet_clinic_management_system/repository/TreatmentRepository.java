package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.TreatmentEntity;

import java.util.List;

public interface TreatmentRepository {
    void save(TreatmentEntity treatmentEntity);
    TreatmentEntity findById(Integer id);
    void update(TreatmentEntity treatmentEntity);
    List<TreatmentEntity> findAll();
    void delete(Integer id);
}
