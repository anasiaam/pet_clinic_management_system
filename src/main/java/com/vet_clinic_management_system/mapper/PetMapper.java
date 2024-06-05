package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.entity.*;

import java.time.LocalDate;

public class PetMapper {
    private PetMapper() {
        // konstruktor privat
    }

    public static PetEntity toEntity(PetDTO petDTO, PetOwnerEntity petOwnerEntity) {
        PetEntity petEntity = new PetEntity();
        petEntity.setCreatedAt(LocalDate.now());
        petEntity.setName(petDTO.getName());
        petEntity.setSpecies(petDTO.getSpecies());
        petEntity.setBreed(petDTO.getBreed());
        petEntity.setAge(petDTO.getAge());
        petEntity.setPetOwner(petOwnerEntity);
        return petEntity;
    }

    public static PetEntity toEntityForUpdate(PetEntity petEntity, PetDTO petDTO) {
        petEntity.setName(petDTO.getName());
        petEntity.setSpecies(petDTO.getSpecies());
        petEntity.setBreed(petDTO.getBreed());
        petEntity.setAge(petDTO.getAge());
        return petEntity;
    }

    public static PetDTO toDTO(PetEntity petEntity) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(petEntity.getId());
        petDTO.setName(petEntity.getName());
        petDTO.setSpecies(petEntity.getSpecies());
        petDTO.setBreed(petEntity.getBreed());
        petDTO.setAge(petEntity.getAge());
        petDTO.setCreatedAt(petEntity.getCreatedAt());
        petDTO.setPetOwner(PetOwnerMapper.toDTO(petEntity.getPetOwner()));
        return petDTO;
    }
}
