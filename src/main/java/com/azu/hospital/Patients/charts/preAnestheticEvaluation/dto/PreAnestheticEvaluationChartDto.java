package com.azu.hospital.Patients.charts.preAnestheticEvaluation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.Plan;
import com.azu.hospital.Patients.charts.physical_assment.AsaStatus;
import com.azu.hospital.Patients.charts.physical_assment.LabsECG;
import com.azu.hospital.Patients.charts.physical_assment.PhysicalExam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PreAnestheticEvaluationChartDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Pre Anesthetic Evaluation Chart";
    private String proposedProcedure;
    private PhysicalExam physicalExam;
    private LabsECG labsEcg;
    private AsaStatus asa;
    private Plan plan;
    private String anesthesiologist;
    private String date;
    private String time;
    private Long createdBy;
    private Long LastModifiedBy;
    private String title = "PRE OPERATIVE ANESTHETIC EVALUATION";
    private String link = "anesthetic";

    public PreAnestheticEvaluationChartDto() {
    }

    public PreAnestheticEvaluationChartDto(
            Long id,
            String chartName,
            String proposedProcedure,
            PhysicalExam physicalExam,
            LabsECG labsEcg,
            AsaStatus asa,
            Plan plan,
            String anesthesiologist,
            String date,
            String time,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.proposedProcedure = proposedProcedure;
        this.physicalExam = physicalExam;
        this.labsEcg = labsEcg;
        this.asa = asa;
        this.plan = plan;
        this.anesthesiologist = anesthesiologist;
        this.date = date;
        this.time = time;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
