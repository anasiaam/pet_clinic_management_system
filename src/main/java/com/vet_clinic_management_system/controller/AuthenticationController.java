package com.vet_clinic_management_system.controller;

import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.SignUpDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.config.UserAuthenticationProvider;
import com.vet_clinic_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/signIn")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO) throws ParseException {
        var authentication = userAuthenticationProvider.validateCredentials(credentialsDTO);

        UserDTO userDTO = (UserDTO) authentication.getPrincipal();
        userDTO.setToken(userAuthenticationProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> register(@RequestBody SignUpDTO signUpDTO) {
        UserDTO createdUser = userService.signUp(signUpDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDTO signUpDTO) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

}
