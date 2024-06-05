package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.BillEntity;

import java.util.List;

public interface BillRepository {
    void save(BillEntity billEntity);
    BillEntity findById(Integer id);
    void update(BillEntity billEntity);
    List<BillEntity> findAll();
    void delete(Integer id);
}
