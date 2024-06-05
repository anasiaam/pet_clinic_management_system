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
public class BillDTO {
    public Integer id;
    @NotNull(message = "{validation.entity.bills.amount}")
    private int amount;
    private LocalDate billingDate;
    private PetDTO pet;
}
