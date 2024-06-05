package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    void save(AppointmentDTO appointmentDTO);
    AppointmentDTO findById(Integer id);
    void update(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> findAll();
    void delete(Integer id);
}
