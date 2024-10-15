package com.azu.hospital.Patients.charts.patientAssessment.utils;

import com.azu.hospital.Patients.charts.patientAssessment.request.PatientAssessmentChartRequest;
import com.azu.hospital.Patients.charts.patientAssessment.entity.PatientAssessmentChart;

public class PatientAssessmentRequestToChart {
    public static PatientAssessmentChart requestToChart(PatientAssessmentChartRequest request){
        return new PatientAssessmentChart.Builder()
                .withDiagnosis(request. getDiagnosis())
                .withProcedures(request.getProcedures())
                .withSkinCondition(request.getSkinCondition())
                .withPhysicalLimitations(request.getPhysicalLimitations())
                .withPatientLOC(request.getLoc())
                .withIVSite(request.getIvSite())
                .withPreProcedureChecklist(request.getPreProcedureChecklist())
                .build();
    }
}
