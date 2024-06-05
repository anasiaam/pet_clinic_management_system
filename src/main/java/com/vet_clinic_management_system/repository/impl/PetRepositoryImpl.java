package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.repository.PetRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetRepositoryImpl implements PetRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(PetEntity petEntity) {
        entityManager.persist(petEntity);
    }

    @Override
    public PetEntity findById(Integer id) {
        return entityManager.find(PetEntity.class, id);
    }

    @Override
    public void update(PetEntity petEntity) {
        entityManager.merge(petEntity);
    }

    @Override
    public List<PetEntity> findAll() {
        return entityManager.createQuery("SELECT p FROM PetEntity p", PetEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
