package com.vet_clinic_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @NotNull(message = "{validation.entity.appointments.appointmentDate}")
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    @NotNull(message = "{validation.entity.appointments.reason}")
    @Column(name = "reason")
    private String reason;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id=" + id +
                ", appointmentDate=" + appointmentDate +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                ", petId=" + pet.getId() +
                ", userId=" + user.getId() +
                '}';
    }
}
