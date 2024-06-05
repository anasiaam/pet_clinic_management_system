package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.AppointmentDTO;
import com.vet_clinic_management_system.entity.AppointmentEntity;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.mapper.AppointmentMapper;
import com.vet_clinic_management_system.repository.AppointmentRepository;
import com.vet_clinic_management_system.repository.PetRepository;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.AppointmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PetRepository petRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(AppointmentDTO appointmentDTO) {
        PetEntity petEntity = petRepository.findById(
                appointmentDTO.getPet().getId()
        );
        UserEntity userEntity = userRepository.findById(
                appointmentDTO.getUser().getId()
        );

        if(petEntity == null || userEntity == null) {
            throw new RuntimeException("petEntity or userEntity does not exist");
        }

        AppointmentEntity appointmentEntity = AppointmentMapper.toEntity(appointmentDTO, petEntity, userEntity);
        this.appointmentRepository.save(appointmentEntity);
    }

    @Override
    public AppointmentDTO findById(Integer id) {
        AppointmentEntity appointmentEntity = this.appointmentRepository.findById(id);
        if(appointmentEntity != null) {
            return AppointmentMapper.toDTO(appointmentEntity);
        } throw new RuntimeException("Appointment with id "+ id +" not found.");
    }

    @Override
    public void update(AppointmentDTO appointmentDTO) {
        AppointmentEntity appointmentEntity = this.appointmentRepository.findById(appointmentDTO.getId());
        if(appointmentEntity != null) {
            appointmentEntity = AppointmentMapper.toEntityForUpdate(appointmentEntity, appointmentDTO);
            appointmentRepository.update(appointmentEntity);
        } else {
            throw new RuntimeException("Appointment with id "+ appointmentDTO.getId() + " not found.");
        }
    }

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream().map(AppointmentMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        appointmentRepository.delete(id);
    }
}
