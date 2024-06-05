package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.PetDTO;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.PetOwnerEntity;
import com.vet_clinic_management_system.mapper.PetMapper;
import com.vet_clinic_management_system.repository.*;
import com.vet_clinic_management_system.service.PetService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetOwnerRepository petOwnerRepository;

    public PetServiceImpl(PetRepository petRepository, PetOwnerRepository petOwnerRepository) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public void save(PetDTO petDTO) {
        PetOwnerEntity petOwnerEntity = petOwnerRepository.findById(
                petDTO.getPetOwner().getId()
        );

        if(petOwnerEntity == null) {
            throw new RuntimeException("petOwnerEntity does not exist.");
        }

        PetEntity petEntity = PetMapper.toEntity(petDTO, petOwnerEntity);
        this.petRepository.save(petEntity);
    }

    @Override
    public PetDTO findById(Integer id) {
        PetEntity petEntity = this.petRepository.findById(id);
        if(petEntity != null) {
            return PetMapper.toDTO(petEntity);
        } throw new RuntimeException("Pet with id "+ id +" not found.");
    }

    @Override
    public void update(PetDTO petDTO) {
        PetEntity petEntity = this.petRepository.findById(petDTO.getId());
        if(petEntity != null) {
            petEntity = PetMapper.toEntityForUpdate(petEntity, petDTO);
            petRepository.update(petEntity);
        } else {
            throw new RuntimeException("Pet with id "+ petDTO.getId() +" not found.");
        }
    }

    @Override
    public List<PetDTO> findAll() {
        return petRepository.findAll().stream().map(PetMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        petRepository.delete(id);
    }
}
