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
public class MedicalRecordDTO {
    public Integer id;
    @NotNull(message = "{validation.entity.medicalRecords.diagnosis}")
    private String diagnosis;
    @NotNull(message = "{validation.entity.medicalRecords.description}")
    private String description;
    @NotNull(message = "{validation.entity.medicalRecords.visitDate}")
    private LocalDate visitDate;
    private LocalDate createdAt;
    private PetDTO pet;
    private UserDTO user;
    private TreatmentDTO treatment;
}
