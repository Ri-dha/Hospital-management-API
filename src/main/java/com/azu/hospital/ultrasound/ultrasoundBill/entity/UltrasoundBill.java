package com.azu.hospital.ultrasound.ultrasoundBill.entity;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "ultrasound_bill")
@Getter
@Setter
public class UltrasoundBill {


    @Id
    @GeneratedValue(
            generator = "ultrasound_bill_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "ultrasound_bill_id_seq",
            sequenceName = "ultrasound_bill_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "text")
    private String note;

    @Column(columnDefinition = "numeric(10,2)")
    private BigDecimal price;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UltrasoundTypeEnum type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private BaseUser accountant;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;

    public UltrasoundBill() {
    }

    public UltrasoundBill(String note, BigDecimal price, UltrasoundTypeEnum type) {
        this.note = note;
        this.price = price;
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
