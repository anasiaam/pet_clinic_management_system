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
@Table(name = "pets")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @NotNull(message = "{validation.entity.pets.name}")
    @Column(name = "name")
    private String name;
    @NotNull(message = "{validation.entity.pets.species}")
    @Column(name = "species")
    private String species;
    @NotNull(message = "{validation.entity.pets.breed}")
    @Column(name = "breed")
    private String breed;
    @NotNull(message = "{validation.entity.pets.age}")
    @Column(name = "age")
    private int age;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PetOwnerEntity petOwner;
    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
    private List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();
    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
    private List<AppointmentEntity> appointmentEntities = new ArrayList<>();
    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER)
    private List<BillEntity> billEntities = new ArrayList<>();

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", ownerId=" + petOwner.getId() +
                '}';
    }
}
