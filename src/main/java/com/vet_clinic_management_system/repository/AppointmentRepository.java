package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentRepository {
    void save(AppointmentEntity appointmentEntity);
    AppointmentEntity findById(Integer id);
    void update(AppointmentEntity appointmentEntity);
    List<AppointmentEntity> findAll();
    void delete(Integer id);
}
