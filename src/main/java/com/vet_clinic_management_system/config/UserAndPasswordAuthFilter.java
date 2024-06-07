package com.vet_clinic_management_system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet_clinic_management_system.DTO.CredentialsDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAndPasswordAuthFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;
    @Lazy
    private final UserAuthenticationProvider provider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/v1/auth/logIn".equals(request.getServletPath())
        && HttpMethod.POST.matches(request.getMethod())) {
            CredentialsDTO credentialsDTO = mapper.readValue(request.getInputStream(), CredentialsDTO.class);
            // lexojme kredencialet nga request-i
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        provider.validateCredentials(credentialsDTO)); // validojme kredencialet me provider-in
                // nese jane te sakta mbushim securityContext, perndryshe bejme clear securityContext dhe hedhim exception
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
        // therrasim doFilter qe te vazhdojme me filterat e tjere
    }
}
