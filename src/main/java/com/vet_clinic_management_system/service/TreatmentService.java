package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.TreatmentDTO;

import java.util.List;

public interface TreatmentService {
    void save(TreatmentDTO treatmentDTO);
    TreatmentDTO findById(Integer id);
    void update(TreatmentDTO treatmentDTO);
    List<TreatmentDTO> findAll();
    void delete(Integer id);
}
