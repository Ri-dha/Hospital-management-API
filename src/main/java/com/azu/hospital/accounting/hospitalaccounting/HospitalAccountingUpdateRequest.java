package com.azu.hospital.accounting.hospitalaccounting;

import java.util.Date;

public record HospitalAccountingUpdateRequest(
        String operationName,
        long operationId,
        long patientId,
        double hospitalPercentage,
        double operationCost,
        String patientName,
        Date date
) {
}
