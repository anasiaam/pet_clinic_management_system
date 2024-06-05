package com.vet_clinic_management_system.repository;


import com.vet_clinic_management_system.entity.PetEntity;

import java.util.List;

public interface PetRepository {
    void save(PetEntity petEntity);
    PetEntity findById(Integer id);
    void update(PetEntity petEntity);
    List<PetEntity> findAll();
    void delete(Integer id);
}
