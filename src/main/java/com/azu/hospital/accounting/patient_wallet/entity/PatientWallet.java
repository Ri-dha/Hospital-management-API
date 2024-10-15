package com.azu.hospital.accounting.patient_wallet.entity;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "patient_wallets")
@Data
public class PatientWallet {


    @Id
    @SequenceGenerator(name = "patient_wallets_id_seq", sequenceName = "patient_wallets_id_seq")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_wallets_id"
    )

    private Long id;

    private BigDecimal balance;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public PatientWallet() {
    }

    public PatientWallet(Patient patient, BigDecimal balance) {
        this.patient = patient;
        this.balance = balance;
    }


    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


}
