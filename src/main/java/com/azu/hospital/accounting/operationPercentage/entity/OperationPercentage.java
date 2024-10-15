package com.azu.hospital.accounting.operationPercentage.entity;


import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "operation_percentage")
public class OperationPercentage {
    @Id
    @SequenceGenerator(name = "ecg_id_seq" , sequenceName = "ecg_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private BigDecimal doctorPercentage;
    private BigDecimal anesthetistDoctorPercentage;
    private BigDecimal permanentPercentage;
    private BigDecimal pharmacistPercentage;
    private BigDecimal nursePercentage;
    private BigDecimal anesthetistPercentage;
    private BigDecimal hospitalPercentage = BigDecimal.ZERO;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "surgeon_id")
    private MainSurgicalList surgicalList;

    @Column(updatable = false)
    private Instant createdAt;
    @Column(insertable = false )
    private Instant updatedAt;

    public OperationPercentage() {
    }

    public OperationPercentage(BigDecimal doctorPercentage,
                               BigDecimal anesthetistDoctorPercentage,
                               BigDecimal permanentPercentage, BigDecimal pharmacistPercentage,
                               BigDecimal nursePercentage, BigDecimal anesthetistPercentage,
                               BigDecimal hospitalPercentage
    ) {
        this.doctorPercentage = doctorPercentage;
        this.anesthetistDoctorPercentage = anesthetistDoctorPercentage;
        this.permanentPercentage = permanentPercentage;
        this.pharmacistPercentage = pharmacistPercentage;
        this.nursePercentage = nursePercentage;
        this.anesthetistPercentage = anesthetistPercentage;
        this.hospitalPercentage = hospitalPercentage;
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
