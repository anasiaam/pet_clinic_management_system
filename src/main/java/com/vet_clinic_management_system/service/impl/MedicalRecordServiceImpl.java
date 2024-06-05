package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.MedicalRecordDTO;
import com.vet_clinic_management_system.entity.MedicalRecordEntity;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.TreatmentEntity;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.mapper.MedicalRecordMapper;
import com.vet_clinic_management_system.repository.MedicalRecordRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.repository.TreatmentRepository;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.MedicalRecordService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final TreatmentRepository treatmentRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository, PetRepository petRepository, UserRepository userRepository, TreatmentRepository treatmentRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public void save(MedicalRecordDTO medicalRecordDTO) {
        PetEntity petEntity = petRepository.findById(
                medicalRecordDTO.getPet().getId()
        );
        UserEntity userEntity = userRepository.findById(
                medicalRecordDTO.getUser().getId()
        );
        TreatmentEntity treatmentEntity = treatmentRepository.findById(
                medicalRecordDTO.getTreatment().getId()
        );

        if(petEntity == null || userEntity == null || treatmentEntity == null) {
            throw new RuntimeException("petEntity or userEntity or treatmentEntity does not exist.");
        }

        MedicalRecordEntity medicalRecordEntity = MedicalRecordMapper.toEntity(medicalRecordDTO, petEntity, userEntity, treatmentEntity);
        this.medicalRecordRepository.save(medicalRecordEntity);
    }

    @Override
    public MedicalRecordDTO findById(Integer id) {
        MedicalRecordEntity medicalRecordEntity = this.medicalRecordRepository.findById(id);
        if(medicalRecordEntity != null) {
            return MedicalRecordMapper.toDTO(medicalRecordEntity);
        } throw new RuntimeException("Medical record with id "+ id +" not found.");
    }

    @Override
    public void update(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecordEntity medicalRecordEntity = this.medicalRecordRepository.findById(medicalRecordDTO.getId());
        if(medicalRecordEntity != null) {
            medicalRecordEntity = MedicalRecordMapper.toEntityForUpdate(medicalRecordEntity, medicalRecordDTO);
            medicalRecordRepository.update(medicalRecordEntity);
        } else {
            throw new RuntimeException("Medical record with id "+ medicalRecordDTO.getId() +" not found.");
        }
    }

    @Override
    public List<MedicalRecordDTO> findAll() {
        return medicalRecordRepository.findAll().stream().map(MedicalRecordMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        medicalRecordRepository.delete(id);
    }
}
