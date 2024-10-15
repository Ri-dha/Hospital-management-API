package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.request;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedsPerMDList;
import com.azu.hospital.Patients.charts.physical_assment.MedsPerMD;
import com.azu.hospital.Patients.charts.physical_assment.PatientLimitation;
import com.azu.hospital.Patients.charts.physical_assment.PatientPositions;
import com.azu.hospital.Patients.charts.physical_assment.ProcedureArea;

import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

public record NonSedatedPatientAssessmentRequest(
        PatientPositions patientPositions,
        PatientLimitation patientLimitation,
        ProcedureArea procedureArea,
        MedsPerMD medsPerMD,
        Set<MedsPerMDEntity> medsPerMDList,
        String medsPerMDListOther,
        String nursesNotes,
        String postOperativeDiagnosis,
        String procedurePerformed,
        String timeStart,
        String timeFinish
) {
}
