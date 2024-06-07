package com.vet_clinic_management_system.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDTO {
    private Integer id;
    @NotNull(message = "{validation.entity.appointments.appointmentDate}")
    private LocalDate appointmentDate;
    @NotNull(message = "{validation.entity.appointments.reason}")
    private String reason;
    private LocalDate createdAt;
    private PetDTO pet;
    private UserDTO user;
}
