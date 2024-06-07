package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.SignUpDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void save(UserDTO userDTO);
    UserDTO findById(Integer id);
    void update(UserDTO userDTO);
    List<UserDTO> findAll();
    void delete(Integer id);
    UserDTO findByUsername(String username);
    UserDTO signUp(SignUpDTO signUpDTO);
}
