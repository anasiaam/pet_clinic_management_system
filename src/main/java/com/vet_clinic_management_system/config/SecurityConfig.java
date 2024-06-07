package com.vet_clinic_management_system.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Lazy
    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint; // merret me exceptions ne response body
    @Lazy
    private final UserAndPasswordAuthFilter userAndPasswordAuthFilter;
    @Lazy
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling(e -> e.authenticationEntryPoint(userAuthenticationEntryPoint));

        http
                .addFilterBefore(userAndPasswordAuthFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, UserAndPasswordAuthFilter.class);

        http.csrf(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers(HttpMethod.POST, "/v1/auth/signIn", "/v1/auth/signUp").permitAll()
                    .anyRequest().authenticated();
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
