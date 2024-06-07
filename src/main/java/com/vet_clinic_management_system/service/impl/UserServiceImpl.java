package com.vet_clinic_management_system.service.impl;

import com.vet_clinic_management_system.DTO.SignUpDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.mapper.UserMapper;
import com.vet_clinic_management_system.repository.UserRepository;
import com.vet_clinic_management_system.service.AuthenticationService;
import com.vet_clinic_management_system.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationService authenticationService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDTO findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            return UserMapper.toDTO(userEntity);
        }
        throw new RuntimeException("User with username " + username + " not found.");
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {
        String encodedPassword = passwordEncoder.encode(new String(signUpDTO.getPassword()));
        UserEntity userEntity = new UserEntity(signUpDTO.getFirstName(), signUpDTO.getLastName(), signUpDTO.getLogin(), encodedPassword);
        userRepository.save(userEntity);
        return UserMapper.toDTO(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationService.findByLogin(username);
    }

}
