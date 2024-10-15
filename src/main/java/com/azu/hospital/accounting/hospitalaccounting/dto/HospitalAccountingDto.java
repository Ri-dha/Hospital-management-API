package com.azu.hospital.accounting.hospitalaccounting.dto;

import java.time.Instant;
import java.util.Date;

public record HospitalAccountingDto (

        long id,
        String operationName,
        long operationId,
        long patientId,
        double hospitalPercentage,
        double operationCost,
        String patientName,
        Date date,
        long createdBy,
        String createdByName,
        long lastModifiedBy,
        String lastModifiedName,

        Instant createdAt,
        Instant updatedAt

) {


}
