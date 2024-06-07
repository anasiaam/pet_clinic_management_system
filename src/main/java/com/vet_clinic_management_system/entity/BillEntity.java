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
@Table(name = "bills")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "{validation.entity.bills.amount}")
    @Column(name = "amount")
    private int amount;
    @Column(name = "billing_date")
    private LocalDate billingDate;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @Override
    public String toString() {
        return "BillEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", billingDate=" + billingDate +
                ", petId=" + pet.getId() +
                '}';
    }
}
