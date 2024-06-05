package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.TreatmentDTO;
import com.vet_clinic_management_system.entity.TreatmentEntity;
import com.vet_clinic_management_system.mapper.TreatmentMapper;
import com.vet_clinic_management_system.repository.TreatmentRepository;
import com.vet_clinic_management_system.service.TreatmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public void save(TreatmentDTO treatmentDTO) {
        TreatmentEntity treatmentEntity = TreatmentMapper.toEntity(treatmentDTO);
        this.treatmentRepository.save(treatmentEntity);
    }

    @Override
    public TreatmentDTO findById(Integer id) {
        TreatmentEntity treatmentEntity = this.treatmentRepository.findById(id);
        if(treatmentEntity != null) {
            return TreatmentMapper.toDTO(treatmentEntity);
        } throw new RuntimeException("Treatment with id "+ id +" not found.");
    }

    @Override
    public void update(TreatmentDTO treatmentDTO) {
        TreatmentEntity treatmentEntity = this.treatmentRepository.findById(treatmentDTO.getId());
        if(treatmentEntity != null) {
            treatmentEntity = TreatmentMapper.toEntityForUpdate(treatmentEntity, treatmentDTO);
            treatmentRepository.update(treatmentEntity);
        } else {
            throw new RuntimeException("Treatment with id "+ treatmentDTO.getId() +" not found.");
        }
    }

    @Override
    public List<TreatmentDTO> findAll() {
        return treatmentRepository.findAll().stream().map(TreatmentMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        treatmentRepository.delete(id);
    }
}
