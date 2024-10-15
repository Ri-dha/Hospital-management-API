package com.azu.hospital.lab_collection.test_list.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "lab_lists")
@Data
public class LabList {

    @Id
    @SequenceGenerator(name = "lab_list_id_seq", sequenceName = "lab_list_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String labName;

    private BigDecimal iqdPrice;

    private BigDecimal usdPrice;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public LabList() {
    }

    public LabList(String labName) {
        this.labName = labName;
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
