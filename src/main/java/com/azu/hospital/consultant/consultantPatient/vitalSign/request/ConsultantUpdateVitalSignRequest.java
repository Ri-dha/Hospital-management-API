package com.azu.hospital.consultant.consultantPatient.vitalSign.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConsultantUpdateVitalSignRequest {

    @NotNull
    private Long vitalSignId;

    private Integer pulseRate;

    private Integer sysBloodPressure;

    private Integer diaBloodPressure;

    private BigDecimal bloodSugar;

    private Float bodyTemp;

    private Float skinTemp;

    private BigDecimal spo;

    private Integer respiratoryRate;

    private BigDecimal painLevel;

    public ConsultantUpdateVitalSignRequest() {
    }
}
