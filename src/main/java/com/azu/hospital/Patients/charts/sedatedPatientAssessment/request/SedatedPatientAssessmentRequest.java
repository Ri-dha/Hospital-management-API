package com.azu.hospital.Patients.charts.sedatedPatientAssessment.request;

import com.azu.hospital.Patients.charts.physical_assment.*;

import java.time.LocalTime;

public record SedatedPatientAssessmentRequest(
        String medications,

        String positiveProblems,

        String preOpMeds,

        String asa,

        PreProcedure preProcedure,

        PreAnesthesiaState preAnesthesiaState,

        PatientSafety patientSafety,

        EyeCare eyeCare,

        AnestheticTechnique anestheticTechnique,

        MonitorsEquipment monitorsEquipment,

        AirwayManagement airwayManagement,

        String anesthesiaStartTime,

        String anesthesiaEndTime,

        String procedureStartTime,

        String procedureEndTime
) {
}
