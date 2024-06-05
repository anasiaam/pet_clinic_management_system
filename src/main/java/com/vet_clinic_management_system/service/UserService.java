package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.UserDTO;

import java.util.List;

public interface UserService {
    void save(UserDTO userDTO);
    UserDTO findById(Integer id);
    void update(UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(Integer id);
}
