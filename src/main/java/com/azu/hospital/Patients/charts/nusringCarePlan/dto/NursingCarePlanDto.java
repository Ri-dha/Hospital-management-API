package com.azu.hospital.Patients.charts.nusringCarePlan.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NursingCarePlanDto extends BaseChartsDto {
    private Long nursingChartId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Nursing Care Plan Chart";

    private String nursingDiagnosis;

    private String goalsAndOutComes;

    private String intervensions;

    private String evaluation;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "NURSING CARE PLAN";
    private String link = "care-plan";


    public NursingCarePlanDto(Long nursingChartId, Long patientId, Long doctorId, String doctorName, String patientName, String patientAge, String patientGender, String wardName,
                              String bedNumber, String chartName, String nursingDiagnosis, String goalsAndOutComes, String intervensions, String evaluation) {
        this.nursingChartId = nursingChartId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.chartName = chartName;
        this.nursingDiagnosis = nursingDiagnosis;
        this.goalsAndOutComes = goalsAndOutComes;
        this.intervensions = intervensions;
        this.evaluation = evaluation;
    }

    public NursingCarePlanDto() {
    }

    public NursingCarePlanDto(Long nursingChartId, String chartName, String nursingDiagnosis, String goalsAndOutComes,
                              String intervensions, String evaluation, Long createdBy, Long LastModifiedBy) {
        this.nursingChartId = nursingChartId;
        this.chartName = chartName;
        this.nursingDiagnosis = nursingDiagnosis;
        this.goalsAndOutComes = goalsAndOutComes;
        this.intervensions = intervensions;
        this.evaluation = evaluation;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
