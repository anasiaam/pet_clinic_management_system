package com.vet_clinic_management_system.service;

import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.UserDTO;

public interface AuthenticationService {
    UserDTO authenticate(CredentialsDTO credentialsDto);
    UserDTO findByLogin(String login);
}
