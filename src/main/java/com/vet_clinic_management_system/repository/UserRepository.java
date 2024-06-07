package com.vet_clinic_management_system.repository;

import com.vet_clinic_management_system.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    void save(UserEntity userEntity);
    UserEntity findById(Integer id);
    void update(UserEntity userEntity);
    List<UserEntity> findAll();
    UserEntity findByUsername(String username);
    void delete(Integer id);
}
