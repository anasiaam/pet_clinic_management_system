package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.AppointmentDTO;
import com.vet_clinic_management_system.entity.AppointmentEntity;
import com.vet_clinic_management_system.entity.PetEntity;
import com.vet_clinic_management_system.entity.UserEntity;

import java.time.LocalDate;

public class AppointmentMapper {
    private AppointmentMapper() {
        // konstruktor privat
    }

    public static AppointmentEntity toEntity(AppointmentDTO appointmentDTO, PetEntity petEntity, UserEntity userEntity) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setCreatedAt(LocalDate.now());
        appointmentEntity.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointmentEntity.setReason(appointmentDTO.getReason());
        appointmentEntity.setPet(petEntity);
        appointmentEntity.setUser(userEntity);
        return appointmentEntity;
    }

    public static AppointmentEntity toEntityForUpdate(AppointmentEntity appointmentEntity, AppointmentDTO appointmentDTO) {
        appointmentEntity.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointmentEntity.setReason(appointmentDTO.getReason());
        return appointmentEntity;
    }

    public static AppointmentDTO toDTO(AppointmentEntity appointmentEntity) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setId(appointmentEntity.getId());
        appointmentDTO.setCreatedAt(appointmentEntity.getAppointmentDate());
        appointmentDTO.setAppointmentDate(appointmentEntity.getAppointmentDate());
        appointmentDTO.setReason(appointmentEntity.getReason());
        appointmentDTO.setPet(PetMapper.toDTO(appointmentEntity.getPet()));
        appointmentDTO.setUser(UserMapper.toDTO(appointmentEntity.getUser()));
        return appointmentDTO;
    }
}
