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
public class PetOwnerDTO {
    private Integer id;
    @NotNull(message = "{validation.entity.petOwners.firstName}")
    private String firstName;
    @NotNull(message = "{validation.entity.petOwners.lastName}")
    private String lastName;
    @NotNull(message = "{validation.entity.petOwners.phoneNumber}")
    private String phoneNumber;
    @NotNull(message = "{validation.entity.petOwners.email}")
    private String email;
    @NotNull(message = "{validation.entity.petOwners.address}")
    private String address;
    private LocalDate createdAt;
}
