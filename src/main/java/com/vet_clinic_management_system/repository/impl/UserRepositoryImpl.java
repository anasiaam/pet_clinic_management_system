package com.vet_clinic_management_system.repository.impl;

import com.vet_clinic_management_system.entity.UserEntity;
import com.vet_clinic_management_system.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(UserEntity userEntity) {
        entityManager.persist(userEntity);
    }

    @Override
    public UserEntity findById(Integer id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public void update(UserEntity userEntity) {
        entityManager.merge(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(findById(id));
    }
}
