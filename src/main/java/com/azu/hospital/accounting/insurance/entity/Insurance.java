package com.azu.hospital.accounting.insurance.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @SequenceGenerator(
            name = "insurance_sequence_id",
            sequenceName = "insurance_sequence_id"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal insuranceAmount = BigDecimal.ZERO;

    private BigDecimal depositAmount = BigDecimal.ZERO;

    private Boolean isArchived = false;

    @OneToOne
    @JoinColumn(
            name = "patient_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "patient_insurance_fk"
            )
    )
    private Patient patient;

    private Instant createdAt;
    private Instant updatedAt;

    public Insurance() {
    }

    public Insurance(BigDecimal insuranceAmount, BigDecimal depositAmount) {
        this.insuranceAmount = insuranceAmount;
        this.depositAmount = depositAmount;
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
