package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.PetOwnerDTO;

import java.util.List;

public interface PetOwnerService {
    void save(PetOwnerDTO petOwnerDTO);
    PetOwnerDTO findById(Integer id);
    void update(PetOwnerDTO petOwnerDTO);
    List<PetOwnerDTO> findAll();
    void delete(Integer id);
}
