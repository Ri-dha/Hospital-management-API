package com.azu.hospital.radiology.radiology_bill_handler.entity;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "radiology_bill_handler")
@Getter
@Setter
public class RadiologyBillHandler {
    @Id
    @GeneratedValue(generator = "radiology_bill_handler_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "radiology_bill_handler_id_seq",
            sequenceName = "radiology_bill_handler_id_seq", allocationSize = 1)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String note;

    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    private RadiologyTypeEnum type;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private BaseUser accountant;

    @Column(updatable = false)
    private Instant createdAt;

    @Column(insertable = false)
    private Instant updatedAt;


    public RadiologyBillHandler() {

    }

    public RadiologyBillHandler(String note, BigDecimal price, RadiologyTypeEnum type) {
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
