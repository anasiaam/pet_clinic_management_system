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
@Table(name = "treatments")
public class TreatmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @NotNull(message = "{validation.entity.treatments.medication}")
    @Column(name = "medication")
    private String medication;
    @NotNull(message = "{validation.entity.treatments.description}")
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @OneToMany(mappedBy = "treatment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();

    @Override
    public String toString() {
        return "TreatmentEntity{" +
                "id=" + id +
                ", medication='" + medication + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
