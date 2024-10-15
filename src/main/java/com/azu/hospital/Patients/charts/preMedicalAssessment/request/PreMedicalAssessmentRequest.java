package com.azu.hospital.Patients.charts.preMedicalAssessment.request;


import com.azu.hospital.Patients.charts.physical_assessment_enm.CardiacCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCategory;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalConditionEnum;
import com.azu.hospital.Patients.charts.physical_assment.*;

import java.util.Map;

public record PreMedicalAssessmentRequest(
        String plannedSurgicalProcedure,

        String surgeryDate,

        String surgeryLocation,

        String attendingSurgeon,

        String presentIllnessHistory,

        String pastMedicalHistoryNote,

        Map<MedicalConditionEnum, Boolean> pastMedicalHistoryTable,

        PriorAnesthesiaComplication priorAnesthesiaComplication,

        SocialHistory socialHistory,

        Map<CardiacCondition, Boolean> cardiacHistory,

        Map<MedicalCategory, Boolean> symptomsSystem,

        Double bmi,

        NormalExamCheck normalExamCheck,

        SurgicalRisk surgicalRisk,

        CardiacRiskAssessment cardiacRiskAssessment
) {
}
