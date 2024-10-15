package com.azu.hospital.Patients.charts.preMedicalAssessment.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.CardiacCondition;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalCategory;
import com.azu.hospital.Patients.charts.physical_assessment_enm.MedicalConditionEnum;
import com.azu.hospital.Patients.charts.physical_assment.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class PreMedicalAssessmentDto {
    private Long id;

    private String chartName = "Pre Medical Assessment Chart";
    private String plannedSurgicalProcedure;

    private String surgeryDate;

    private String surgeryLocation;

    private String attendingSurgeon;

    private String presentIllnessHistory;

    private String pastMedicalHistoryNote;

    private Map<MedicalConditionEnum, Boolean> pastMedicalHistoryTable;

    private PriorAnesthesiaComplication priorAnesthesiaComplication;

    private SocialHistory socialHistory;

    private Map<CardiacCondition, Boolean> cardiacHistory;

    private Map<MedicalCategory, Boolean> symptomsSystem;

    private Double bmi;

    private NormalExamCheck normalExamCheck;

    private SurgicalRisk surgicalRisk;

    private CardiacRiskAssessment cardiacRiskAssessment;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "PRE-OPERATIVE MEDICAL ASSESSMENT";
    private String link = "medical-assessment";

//  private String allergy;

    public PreMedicalAssessmentDto() {
    }

    public PreMedicalAssessmentDto(
            Long id,
            String chartName,
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
            CardiacRiskAssessment cardiacRiskAssessment,
            Long createdBy,
            Long LastModifiedBy
//          String allergy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.plannedSurgicalProcedure = plannedSurgicalProcedure;
        this.surgeryDate = surgeryDate;
        this.surgeryLocation = surgeryLocation;
        this.attendingSurgeon = attendingSurgeon;
        this.presentIllnessHistory = presentIllnessHistory;
        this.pastMedicalHistoryNote = pastMedicalHistoryNote;
        this.pastMedicalHistoryTable = pastMedicalHistoryTable;
        this.priorAnesthesiaComplication = priorAnesthesiaComplication;
        this.socialHistory = socialHistory;
        this.cardiacHistory = cardiacHistory;
        this.symptomsSystem = symptomsSystem;
        this.bmi = bmi;
        this.normalExamCheck = normalExamCheck;
        this.surgicalRisk = surgicalRisk;
        this.cardiacRiskAssessment = cardiacRiskAssessment;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
//    this.allergy = allergy;
    }
}
