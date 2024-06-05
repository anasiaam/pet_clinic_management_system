package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.mapper.UserMapper;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserDTO userDTO) {
        UserEntity userEntity = UserMapper.toEntity(userDTO);
        this.userRepository.save(userEntity);
    }

    @Override
    public UserDTO findById(Integer id) {
        UserEntity userEntity = this.userRepository.findById(id);
        if(userEntity != null) {
            return UserMapper.toDTO(userEntity);
        } throw new RuntimeException("User with id "+ id +" not found.");
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity userEntity = this.userRepository.findById(userDTO.getId());
        if(userEntity != null) {
            userEntity = UserMapper.toEntityForUpdate(userEntity, userDTO);
            userRepository.update(userEntity);
        } else {
            throw new RuntimeException("User with id "+ userDTO.getId() +" not found.");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
