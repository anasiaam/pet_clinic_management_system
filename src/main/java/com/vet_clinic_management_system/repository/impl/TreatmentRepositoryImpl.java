package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.TreatmentEntity;
import com.vet_clinic_management_system.repository.TreatmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TreatmentRepositoryImpl implements TreatmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(TreatmentEntity treatmentEntity) {
        entityManager.persist(treatmentEntity);
    }

    @Override
    public TreatmentEntity findById(Integer id) {
        return entityManager.find(TreatmentEntity.class, id);
    }

    @Override
    public void update(TreatmentEntity treatmentEntity) {
        entityManager.merge(treatmentEntity);
    }

    @Override
    public List<TreatmentEntity> findAll() {
        return entityManager.createQuery("SELECT t FROM TreatmentEntity t", TreatmentEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
