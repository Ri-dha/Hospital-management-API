package com.azu.hospital.Patients.charts.vitalSignChart.entity;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "patient_vital_sign")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class VitalSign {
    @Id
    @SequenceGenerator(name = "patient_vital_sign_seq", sequenceName = "patient_vital_sign_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Patient patient;

    private String chartName = "Vital Sign Chart";

    private BigDecimal painLevel;

    private Integer respiratoryRate;

    private Integer systolicBloodPressure;

    private Integer diastolicBloodPressure;

    private Integer mainBloodPressure;

    // TODO: 9/8/2023 Should be in C or Fahrenheit
    private Float skinTemperature;

    // TODO: 9/8/2023 Should be in C or Fahrenheit
    private Float coreTemperature;

    private BigDecimal spo;

    private BigDecimal bloodSugar;

    private Integer pulseRate;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    @CreatedBy
    @Column(
            updatable = false
    )
    private Long createdBy;
    @org.springframework.data.annotation.LastModifiedBy
    @Column(
            insertable = false
    )
    private Long LastModifiedBy;


    public VitalSign() {
    }

    public VitalSign(
            BigDecimal painLevel,
            Integer respiratoryRate,
            Integer systolicBloodPressure,
            Integer diastolicBloodPressure,
            Integer mainBloodPressure,
            Float skinTemperature,
            Float coreTemperature,
            Integer pulseRate,
            BigDecimal spo,
            BigDecimal bloodSugar
    ) {
        this.painLevel = painLevel;
        this.respiratoryRate = respiratoryRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.mainBloodPressure = mainBloodPressure;
        this.skinTemperature = skinTemperature;
        this.coreTemperature = coreTemperature;
        this.pulseRate = pulseRate;
        this.spo = spo;
        this.bloodSugar = bloodSugar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

