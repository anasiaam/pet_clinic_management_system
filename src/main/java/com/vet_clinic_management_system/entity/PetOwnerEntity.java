package com.vet_clinic_management_system.entity;

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
@Table(name = "pet_owners")
public class PetOwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "{validation.entity.petOwners.firstName}")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "{validation.entity.petOwners.lastName}")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "{validation.entity.petOwners.phoneNumber}")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull(message = "{validation.entity.petOwners.email}")
    @Column(name = "email")
    private String email;
    @NotNull(message = "{validation.entity.petOwners.address}")
    @Column(name = "address")
    private String address;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "petOwner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PetEntity> petEntities = new ArrayList<>();

    @Override
    public String toString() {
        return "PetOwnerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
