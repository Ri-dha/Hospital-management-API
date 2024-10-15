package com.azu.hospital.Patients.charts.preAdvanceDirective.request;


import java.time.LocalDate;
import java.time.LocalTime;

public record PreAdvanceDirectiveRequest(
        String option,

        String printName,

        String signature,

        String date,

        String preOpNurseSignature,

        String preOpNurseDate,

        String preOpNurseTime
) {
}
