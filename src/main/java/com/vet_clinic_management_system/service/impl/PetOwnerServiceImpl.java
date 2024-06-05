package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.PetOwnerDTO;
import com.vet_clinic_management_system.entity.PetOwnerEntity;
import com.vet_clinic_management_system.mapper.PetOwnerMapper;
import com.vet_clinic_management_system.repository.PetOwnerRepository;
import com.vet_clinic_management_system.service.PetOwnerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PetOwnerServiceImpl implements PetOwnerService {
    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public void save(PetOwnerDTO petOwnerDTO) {
        PetOwnerEntity petOwnerEntity = PetOwnerMapper.toEntity(petOwnerDTO);
        this.petOwnerRepository.save(petOwnerEntity);
    }

    @Override
    public PetOwnerDTO findById(Integer id) {
        PetOwnerEntity petOwnerEntity = this.petOwnerRepository.findById(id);
        if(petOwnerEntity != null) {
            return PetOwnerMapper.toDTO(petOwnerEntity);
        } throw new RuntimeException("Pet Owner with id "+ id +" not found.");
    }

    @Override
    public void update(PetOwnerDTO petOwnerDTO) {
        PetOwnerEntity petOwnerEntity = this.petOwnerRepository.findById(petOwnerDTO.getId());
        if(petOwnerEntity != null) {
            petOwnerEntity = PetOwnerMapper.toEntityForUpdate(petOwnerEntity, petOwnerDTO);
            petOwnerRepository.update(petOwnerEntity);
        } else {
            throw new RuntimeException("Pet owner with id "+ petOwnerDTO.getId() +" not found.");
        }
    }

    @Override
    public List<PetOwnerDTO> findAll() {
        return petOwnerRepository.findAll().stream().map(PetOwnerMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        petOwnerRepository.delete(id);
    }
}
