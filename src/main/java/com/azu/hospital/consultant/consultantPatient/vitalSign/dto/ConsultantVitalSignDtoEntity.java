package com.azu.hospital.consultant.consultantPatient.vitalSign.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ConsultantVitalSignDtoEntity {

    private Integer pulseRate;

    private Integer sysBloodPressure;

    private Integer diaBloodPressure;

    private BigDecimal bloodSugar;

    private Float bodyTemp;

    private Float skinTemp;

    private BigDecimal spo;

    private Integer respiratoryRate;

    private BigDecimal painLevel;

    public ConsultantVitalSignDtoEntity(
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

    public ConsultantVitalSignDtoEntity() {
    }
}