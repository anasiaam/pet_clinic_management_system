package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.AppointmentEntity;
import com.vet_clinic_management_system.repository.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(AppointmentEntity appointmentEntity) {
        entityManager.persist(appointmentEntity);
    }

    @Override
    public AppointmentEntity findById(Integer id) {
        return entityManager.find(AppointmentEntity.class, id);
    }

    @Override
    public void update(AppointmentEntity appointmentEntity) {
        entityManager.merge(appointmentEntity);
    }

    @Override
    public List<AppointmentEntity> findAll() {
        return entityManager.createQuery("SELECT a FROM AppointmentEntity a", AppointmentEntity.class).getResultList();
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
