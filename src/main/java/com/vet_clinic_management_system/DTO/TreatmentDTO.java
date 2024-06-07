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
public class TreatmentDTO {
    private Integer id;
    @NotNull(message = "{validation.entity.treatments.medication}")
    private String medication;
    @NotNull(message = "{validation.entity.treatments.description}")
    private String description;
    private LocalDate createdAt;
}
