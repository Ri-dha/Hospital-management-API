package com.azu.hospital.accounting.hospitalaccounting.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "hospital_accounting")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class HospitalAccounting {

   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long id;

   private Long operationId;

   private String operationName;

   private Double operationCost = 0.0;

   private Double hospitalIncome = 0.0;

   private Long patientId;

   private String patientName;

   private boolean isArchived = false;

   private Date date = new Date(3/2024);

    @Column(updatable = false)
    private Instant createAt;

    @Column(insertable = false)
    private Instant updateAt;


    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long createdBy;
    @org.springframework.data.annotation.LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    public HospitalAccounting() {
    }

    public HospitalAccounting(Long operationId, String operationName,
                              Double operationCost, Double hospitalIncome,
                              Long patientId, String patientName, Date date, Instant createAt, Instant updateAt, Long createdBy, Long lastModifiedBy) {
        this.operationId = operationId;
        this.operationName = operationName;
        this.operationCost = operationCost;
        this.hospitalIncome = hospitalIncome;
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.createdBy = createdBy;
        LastModifiedBy = lastModifiedBy;
    }
}
