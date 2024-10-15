package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity;

import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import com.azu.hospital.utils.enums.patient.PatientTransactionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "patient_transaction_lists")
@Data
public class PatientTransactionList {

    @Id
    @SequenceGenerator(
            name = "patient_transaction_lists_id_seq",
            sequenceName = "patient_transaction_lists_id_seq",
    allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_wallet_id")
    private PatientWallet patientWallet;

    private BigDecimal amount;

    private PatientTransactionType type;



    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;


    public PatientTransactionList() {
    }

    public PatientTransactionList(PatientWallet patientWallet, BigDecimal amount , PatientTransactionType type) {
        this.patientWallet = patientWallet;
        this.amount = amount;
        this.type = type;
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
