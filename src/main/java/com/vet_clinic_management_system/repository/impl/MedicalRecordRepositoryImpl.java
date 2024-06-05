package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.MedicalRecordEntity;
import com.vet_clinic_management_system.repository.MedicalRecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepositoryImpl implements MedicalRecordRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MedicalRecordEntity medicalRecordEntity) {
        entityManager.persist(medicalRecordEntity);
    }

    @Override
    public MedicalRecordEntity findById(Integer id) {
        return entityManager.find(MedicalRecordEntity.class, id);
    }

    @Override
    public void update(MedicalRecordEntity medicalRecordEntity) {
        entityManager.merge(medicalRecordEntity);
    }

    @Override
    public List<MedicalRecordEntity> findAll() {
        return entityManager.createQuery("SELECT m FROM MedicalRecordEntity m", MedicalRecordEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
