package com.azu.hospital.Patients.charts.discharge.request;

import java.time.Instant;

public record DischargeRequest(

        String treatmentOnDischarge,
        String recommendation,
        String surgicalMedicalProcedure,
        String dischargeTime,
        String dischargedDate

) {
}
