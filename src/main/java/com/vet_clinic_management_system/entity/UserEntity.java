package com.vet_clinic_management_system.entity;

import com.vet_clinic_management_system.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "{validation.entity.users.username}")
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @NotNull(message = "{validation.entity.users.password}")
    @Column(name = "password", nullable = false)
    private String password;
    @NotNull(message = "{validation.entity.users.email}")
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @NotNull(message = "{validation.entity.users.firstName}")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotNull(message = "{validation.entity.users.lastName}")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotNull(message = "{validation.entity.users.phoneNumber}")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @NotNull(message = "{validation.entity.users.role}")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointmentEntities = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();

    public UserEntity(String username, String password, String email, String firstName, String lastName, String phoneNumber, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
