package com.vet_clinic_management_system.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vet_clinic_management_system.DTO.CredentialsDTO;
import com.vet_clinic_management_system.DTO.UserDTO;
import com.vet_clinic_management_system.service.impl.AuthenticationServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationProvider {
    private static final String ID = "id";
    private static final String ROLES = "roles";
    private static final String ROLES_DELIMITER = ",";

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private final AuthenticationServiceImpl authenticationService;

    public UserAuthenticationProvider(@Lazy AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void init() { // qe mos ta kemi secret key te lexueshem ne JVM e enkodojme ne base64
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDTO userDTO) {
        Date now = new Date();
        Date validity = new Date(now.getTime() +3600000);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(userDTO.getUsername())
                .withClaim(ID, userDTO.getId())
                .withClaim(ROLES, userDTO.getAuthorities().stream().map(Object::toString).collect(Collectors.joining(ROLES_DELIMITER)))
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token); // hedh exception nese JWT ka skaduar

        UserDTO userDTO = authenticationService.findByLogin(decodedJWT.getIssuer()); // kerkojme per user-in
        // krijojme nje authentication user bean
        return new UsernamePasswordAuthenticationToken(userDTO, null, Collections.emptyList());
    }

    public Authentication validateCredentials(CredentialsDTO credentialsDTO) {
        UserDTO userDTO = authenticationService.authenticate(credentialsDTO);
        return new UsernamePasswordAuthenticationToken(userDTO, null, Collections.emptyList());
    }

}
