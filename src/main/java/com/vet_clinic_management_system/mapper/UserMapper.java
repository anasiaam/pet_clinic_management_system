package com.vet_clinic_management_system.mapper;

import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;

import java.time.LocalDate;

public class UserMapper {
    private UserMapper() {
        // konstruktor privat
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCreatedAt(LocalDate.now());
        userEntity.setRole(userDTO.getRole());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        return userEntity;
    }

    public static UserEntity toEntityForUpdate(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setRole(userDTO.getRole());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        return userEntity;
    }

    public static UserDTO toDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setRole(userEntity.getRole());
        userDTO.setPhoneNumber(userEntity.getPhoneNumber());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        return userDTO;
    }
}
