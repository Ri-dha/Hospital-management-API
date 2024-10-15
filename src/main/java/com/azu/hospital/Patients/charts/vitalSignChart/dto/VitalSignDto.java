package com.azu.hospital.Patients.charts.vitalSignChart.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class VitalSignDto {
    private Long id;

    private String chartName = "Vital Sign Chart";

    private BigDecimal painLevel;

    private Integer respiratoryRate;

    private Integer systolicBloodPressure;

    private Integer diastolicBloodPressure;

    private Integer mainBloodPressure;

    private Float skinTemperature;

    private Float coreTemperature;

    private Integer pulseRate;

    private BigDecimal spo;

    private BigDecimal bloodSugar;

    private Instant createdAt;

    private Long createdBy;
    private Long LastModifiedBy;

    public VitalSignDto() {
    }

    public VitalSignDto(
            Long id,
            String chartName,
            BigDecimal painLevel,
            Integer respiratoryRate,
            Integer systolicBloodPressure,
            Integer diastolicBloodPressure,
            Integer mainBloodPressure,
            Float skinTemperature,
            Float coreTemperature,
            Integer pulseRate,
            Instant createdAt,
            BigDecimal spo,
            BigDecimal bloodSugar,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.painLevel = painLevel;
        this.respiratoryRate = respiratoryRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.mainBloodPressure = mainBloodPressure;
        this.skinTemperature = skinTemperature;
        this.coreTemperature = coreTemperature;
        this.pulseRate = pulseRate;
        this.createdAt = createdAt;
        this.spo = spo;
        this.bloodSugar = bloodSugar;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
