package com.vet_clinic_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @NotNull(message = "{validation.entity.medicalRecords.diagnosis}")
    @Column(name = "diagnosis")
    private String diagnosis;
    @NotNull(message = "{validation.entity.medicalRecords.description}")
    @Column(name = "description")
    private String description;
    @NotNull(message = "{validation.entity.medicalRecords.visitDate}")
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private TreatmentEntity treatment;

    @Override
    public String toString() {
        return "MedicalRecordEntity{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", description='" + description + '\'' +
                ", visitDate=" + visitDate +
                ", createdAt=" + createdAt +
                ", petId=" + pet.getId() +
                ", userId=" + user.getId() +
                ", treatmentId=" + treatment.getId() +
                '}';
    }
}
