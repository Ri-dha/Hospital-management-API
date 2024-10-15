package com.azu.hospital.accounting.patientBill.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.utils.enums.accounting.PatientBillEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "patient_bills")
@Data
public class PatientBill {

    @Id
    @SequenceGenerator(name = "patient_bill_seq_id" , sequenceName = "patient_bill_seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal fullPrice;

    private BigDecimal discount;

    private String place;

    private PatientBillEnum type;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;


    @ManyToOne
    private Patient patient;

    public PatientBill() {
    }

    public PatientBill(
            String note,
            Integer quantity,
            BigDecimal price,
            BigDecimal fullPrice,
            BigDecimal discount,
            PatientBillEnum type,
            String place
    ) {
        this.note = note;
        this.quantity = quantity;
        this.price = price;
        this.fullPrice = fullPrice;
        this.discount = discount;
        this.type = type;
        this.place = place;
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
