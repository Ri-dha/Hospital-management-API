package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request;


import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record PostOperativeFollowUpCallChartRequest(
        String dateOfCall,

        String lsmCaller,

        Integer numberOfAttempts,

        String time,

        String procedure,

        BigDecimal painLevel,

        String lsmPhysicianSignature,

        String anesthesiologistSignature,

        String dateOfService,
        List<PatientExperiencing> patientExperiencing

        ) {
}
