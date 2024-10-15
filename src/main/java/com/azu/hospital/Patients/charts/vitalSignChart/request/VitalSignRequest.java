package com.azu.hospital.Patients.charts.vitalSignChart.request;

import java.math.BigDecimal;

public record VitalSignRequest(

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
}
