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
public class PetDTO {
    private Integer id;
    @NotNull(message = "{validation.entity.pets.name}")
    private String name;
    @NotNull(message = "{validation.entity.pets.species}")
    private String species;
    @NotNull(message = "{validation.entity.pets.breed}")
    private String breed;
    @NotNull(message = "{validation.entity.pets.age}")
    private int age;
    private LocalDate createdAt;
    private PetOwnerDTO petOwner;
}
