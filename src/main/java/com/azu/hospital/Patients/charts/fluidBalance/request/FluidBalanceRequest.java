package com.azu.hospital.Patients.charts.fluidBalance.request;

import java.math.BigDecimal;

public record FluidBalanceRequest(
        String dateFrom,
        String dateTo,
        BigDecimal ml24InputHour,
        BigDecimal fluidBalance24InputHour,
        String candidate
) {
}
