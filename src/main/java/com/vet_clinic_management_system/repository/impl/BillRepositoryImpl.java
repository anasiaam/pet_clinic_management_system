package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.BillEntity;
import com.vet_clinic_management_system.repository.BillRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillRepositoryImpl implements BillRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(BillEntity billEntity) {
        entityManager.persist(billEntity);
    }

    @Override
    public BillEntity findById(Integer id) {
        return entityManager.find(BillEntity.class, id);
    }

    @Override
    public void update(BillEntity billEntity) {
        entityManager.merge(billEntity);
    }

    @Override
    public List<BillEntity> findAll() {
        return entityManager.createQuery("SELECT b FROM BillEntity b", BillEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
