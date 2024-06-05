package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.PetOwnerEntity;
import com.vet_clinic_management_system.repository.PetOwnerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PetOwnerRepositoryImpl implements PetOwnerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(PetOwnerEntity petOwnerEntity) {
        entityManager.persist(petOwnerEntity);
    }

    @Override
    public PetOwnerEntity findById(Integer id) {
        return entityManager.find(PetOwnerEntity.class, id);
    }

    @Override
    public void update(PetOwnerEntity petOwnerEntity) {
        entityManager.merge(petOwnerEntity);
    }

    @Override
    public List<PetOwnerEntity> findAll() {
        return entityManager.createQuery("SELECT p FROM PetOwnerEntity p", PetOwnerEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
