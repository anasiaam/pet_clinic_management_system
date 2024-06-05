package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.BillDTO;
import com.vet_clinic_management_system.entity.BillEntity;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.mapper.BillMapper;
import com.vet_clinic_management_system.repository.BillRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.service.BillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final PetRepository petRepository;

    public BillServiceImpl(BillRepository billRepository, PetRepository petRepository) {
        this.billRepository = billRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void save(BillDTO billDTO) {
        PetEntity petEntity = petRepository.findById(
                billDTO.getPet().getId()
        );

        if(petEntity == null) {
            throw new RuntimeException("petEntity does not exist");
        }

        BillEntity billEntity = BillMapper.toEntity(billDTO, petEntity);
        this.billRepository.save(billEntity);
    }

    @Override
    public BillDTO findById(Integer id) {
        BillEntity billEntity = this.billRepository.findById(id);
        if(billEntity != null) {
            return BillMapper.toDTO(billEntity);
        } throw new RuntimeException("Bill with id "+ id +" not found.");
    }

    @Override
    public void update(BillDTO billDTO) {
        BillEntity billEntity = this.billRepository.findById(billDTO.getId());
        if(billEntity != null) {
            billEntity = BillMapper.toEntityForUpdate(billEntity, billDTO);
            billRepository.update(billEntity);
        } else {
            throw new RuntimeException("Bill with id "+ billDTO.getId() + " not found.");
        }
    }

    @Override
    public List<BillDTO> findAll() {
        return billRepository.findAll().stream().map(BillMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        billRepository.delete(id);
    }
}
