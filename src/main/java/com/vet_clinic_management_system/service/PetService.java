package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.PetDTO;

import java.util.List;

public interface PetService {
    void save(PetDTO petDTO);
    PetDTO findById(Integer id);
    void update(PetDTO petDTO);
    List<PetDTO> findAll();
    void delete(Integer id);
}
