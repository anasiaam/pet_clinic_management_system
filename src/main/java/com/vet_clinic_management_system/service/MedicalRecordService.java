package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.MedicalRecordDTO;

import java.util.List;

public interface MedicalRecordService {
    void save(MedicalRecordDTO medicalRecordDTO);
    MedicalRecordDTO findById(Integer id);
    void update(MedicalRecordDTO medicalRecordDTO);
    List<MedicalRecordDTO> findAll();
    void delete(Integer id);
}
