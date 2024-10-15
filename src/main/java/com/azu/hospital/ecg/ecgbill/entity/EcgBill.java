package com.azu.hospital.ecg.ecgbill.entity;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Table(name = "ecg_bill")
@Entity
public class EcgBill {
    @Id
    @SequenceGenerator(
            name = "ecg_bill_id_seq",
            sequenceName = "ecg_bill_id_seq",
            allocationSize = 1)
    @GeneratedValue(
            generator = "ecg_bill_id_seq",
            strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(columnDefinition = "text")
    private String note;

    @Column(columnDefinition = "numeric(10,2)")
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private BaseUser accountant;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;

    public EcgBill() {
    }


    public EcgBill(String note, BigDecimal price) {
        this.note = note;
        this.price = price;
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
