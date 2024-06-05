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
    public Integer id;
    @NotNull(message = "{validation.entity.users.username}")
    @Column(name = "username")
    private String username;
    @NotNull(message = "{validation.entity.users.password}")
    @Column(name = "password")
    private String password;
    @NotNull(message = "{validation.entity.users.email}")
    @Column(name = "email")
    private String email;
    @NotNull(message = "{validation.entity.users.firstName}")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "{validation.entity.users.lastName}")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "{validation.entity.users.phoneNumber}")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message = "{validation.entity.users.role}")
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<AppointmentEntity> appointmentEntities = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();

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
