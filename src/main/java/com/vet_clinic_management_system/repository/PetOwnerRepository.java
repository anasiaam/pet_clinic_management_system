package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.PetOwnerEntity;

import java.util.List;

public interface PetOwnerRepository {
    void save(PetOwnerEntity petOwnerEntity);
    PetOwnerEntity findById(Integer id);
    void update(PetOwnerEntity petOwnerEntity);
    List<PetOwnerEntity> findAll();
    void delete(Integer id);
}
