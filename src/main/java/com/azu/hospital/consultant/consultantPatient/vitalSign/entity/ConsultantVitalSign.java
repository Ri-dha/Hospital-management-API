package com.azu.hospital.consultant.consultantPatient.vitalSign.entity;

import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.consultant.consultantPatient.vitalSign.dto.ConsultantVitalSignDtoEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "consultant_patient_vital-sign")
@Getter
@Setter
public class ConsultantVitalSign {

    @Id
    @SequenceGenerator(
            name = "consultant_patient_vital-sign_id" ,
            sequenceName = "consultant_patient_vital-sign_id"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Integer pulseRate;

    private Integer sysBloodPressure;

    private Integer diaBloodPressure;

    private BigDecimal bloodSugar;

    private Float bodyTemp;

    private Float skinTemp;

    private BigDecimal spo;

    private Integer respiratoryRate;

    private BigDecimal painLevel;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;


    public ConsultantVitalSign(ConsultantVitalSignDtoEntity entity) {
        this.pulseRate = entity.getPulseRate();
        this.sysBloodPressure = entity.getSysBloodPressure();
        this.diaBloodPressure = entity.getDiaBloodPressure();
        this.bloodSugar = entity.getBloodSugar();
        this.bodyTemp = entity.getBodyTemp();
        this.skinTemp = entity.getSkinTemp();
        this.spo = entity.getSpo();
        this.respiratoryRate = entity.getRespiratoryRate();
        this.painLevel = entity.getPainLevel();
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = Instant.now();
    }


    @ManyToOne
    @JoinColumn(name = "patient_id")
    private ConsultantPatient consultantPatient;

    public ConsultantVitalSign(
            Integer pulseRate,
            Integer sysBloodPressure,
            Integer diaBloodPressure,
            BigDecimal bloodSugar,
            Float bodyTemp,
            Float skinTemp,
            BigDecimal spo,
            Integer respiratoryRate,
            BigDecimal painLevel
    ) {
        this.pulseRate = pulseRate;
        this.sysBloodPressure = sysBloodPressure;
        this.diaBloodPressure = diaBloodPressure;
        this.bloodSugar = bloodSugar;
        this.bodyTemp = bodyTemp;
        this.skinTemp = skinTemp;
        this.spo = spo;
        this.respiratoryRate = respiratoryRate;
        this.painLevel = painLevel;
    }

    public ConsultantVitalSign() {
    }
}
