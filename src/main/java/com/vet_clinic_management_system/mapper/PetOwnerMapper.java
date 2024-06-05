package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.PetOwnerDTO;
import com.vet_clinic_management_system.entity.PetOwnerEntity;

import java.time.LocalDate;

public class PetOwnerMapper {
    private PetOwnerMapper() {
        // konstruktor privat
    }

    public static PetOwnerEntity toEntity(PetOwnerDTO petOwnerDTO) {
        PetOwnerEntity petOwnerEntity = new PetOwnerEntity();
        petOwnerEntity.setCreatedAt(LocalDate.now());
        petOwnerEntity.setFirstName(petOwnerDTO.getFirstName());
        petOwnerEntity.setLastName(petOwnerDTO.getLastName());
        petOwnerEntity.setPhoneNumber(petOwnerDTO.getPhoneNumber());
        petOwnerEntity.setEmail(petOwnerDTO.getEmail());
        petOwnerEntity.setAddress(petOwnerDTO.getAddress());
        return petOwnerEntity;
    }

    public static PetOwnerEntity toEntityForUpdate(PetOwnerEntity petOwnerEntity, PetOwnerDTO petOwnerDTO) {
        petOwnerEntity.setFirstName(petOwnerDTO.getFirstName());
        petOwnerEntity.setLastName(petOwnerDTO.getLastName());
        petOwnerEntity.setPhoneNumber(petOwnerDTO.getPhoneNumber());
        petOwnerEntity.setEmail(petOwnerDTO.getEmail());
        petOwnerEntity.setAddress(petOwnerDTO.getAddress());
        return petOwnerEntity;
    }

    public static PetOwnerDTO toDTO(PetOwnerEntity petOwnerEntity) {
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();
        petOwnerDTO.setId(petOwnerEntity.getId());
        petOwnerDTO.setFirstName(petOwnerEntity.getFirstName());
        petOwnerDTO.setLastName(petOwnerEntity.getLastName());
        petOwnerDTO.setPhoneNumber(petOwnerEntity.getPhoneNumber());
        petOwnerDTO.setEmail(petOwnerEntity.getEmail());
        petOwnerDTO.setAddress(petOwnerEntity.getAddress());
        petOwnerDTO.setCreatedAt(petOwnerEntity.getCreatedAt());
        return petOwnerDTO;
    }
}
