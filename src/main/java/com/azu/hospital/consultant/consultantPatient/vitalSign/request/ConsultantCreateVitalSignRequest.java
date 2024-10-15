package com.azu.hospital.consultant.consultantPatient.vitalSign.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConsultantCreateVitalSignRequest {

    @NotNull
    private Long patientId;

    @NotNull
    private Integer pulseRate;

    @NotNull
    private Integer sysBloodPressure;

    @NotNull
    private Integer diaBloodPressure;

    @NotNull
    private BigDecimal bloodSugar;

    @NotNull
    private Float bodyTemp;

    @NotNull
    private Float skinTemp;

    @NotNull
    private BigDecimal spo;

    @NotNull
    private Integer respiratoryRate;

    @NotNull
    private BigDecimal painLevel;

    public ConsultantCreateVitalSignRequest() {
    }
}
