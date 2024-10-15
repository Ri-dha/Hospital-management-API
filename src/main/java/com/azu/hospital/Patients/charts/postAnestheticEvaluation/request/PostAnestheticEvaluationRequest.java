package com.azu.hospital.Patients.charts.postAnestheticEvaluation.request;

import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientHas;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record PostAnestheticEvaluationRequest(
        Boolean anesthesiaComplications,
        PatientStatus patientStatus,
        PatientHas patientHas,
        String patientHasOther,
        String postAnesthesiaNote,
        String anesthesiologist,
        String date,
        String time
) {
}
