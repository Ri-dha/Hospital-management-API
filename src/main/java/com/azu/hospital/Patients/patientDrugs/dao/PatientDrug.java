package com.azu.hospital.Patients.patientDrugs.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.Instant;

@Table(name = "patient_drug")
@Entity
@Data
public class PatientDrug {
    @Id
    @SequenceGenerator(
            name = "patient_id_drug_sequence",
            sequenceName = "patient_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_sequence"
    )
    private Long drugId;

    @NotNull(message = "Drug Name is required")
    private String drug;

    private String dose;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Time format should be like HH:MM")
    @NotNull(message = "Time is Required")
    @NotBlank(message = "Time is Required")
    @NotEmpty(message = "Time is Required")
    private String time;

    private String roa;

    @Column(columnDefinition = "TEXT")
    private String note;

    @NotNull(message = "Count is required")
    private Integer count;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @ManyToOne(cascade = CascadeType.ALL,fetch =  FetchType.LAZY)
    private Patient patient;

    public PatientDrug() {
    }

    public PatientDrug(
            String drug,
            String dose,
            String time,
            String roa,
            String note,
            Integer count
    ) {
        this.drug = drug;
        this.dose = dose;
        this.time = time;
        this.roa = roa;
        this.note = note;
        this.count = count;
    }

    public PatientDrug(
            Long drugId,
            String drug,
            String dose,
            String time,
            String roa,
            String note,
            Integer count
    ) {
        this.drugId = drugId;
        this.drug = drug;
        this.dose = dose;
        this.time = time;
        this.roa = roa;
        this.note = note;
        this.count = count;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

}
