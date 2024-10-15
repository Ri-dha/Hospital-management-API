package com.azu.hospital.Patients.charts.postAnestheticEvaluation.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientHas;
import com.azu.hospital.Patients.charts.physical_assessment_enm.PatientStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class PostAnestheticEvaluationDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Post Anesthetic Evaluation Chart";
    private Boolean anesthesiaComplications;
    private PatientStatus patientStatus;
    private PatientHas patientHas;
    private String patientHasOther;
    private String postAnesthesiaNote;
    private String anesthesiologist;
    private String date;
    private String time;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "POST OPERATIVE ANESTHETIC EVALUATION";
    private String link = "evaluation";

    public PostAnestheticEvaluationDto() {
    }

    public PostAnestheticEvaluationDto(
            Long id,
            String chartName,
            Boolean anesthesiaComplications,
            PatientStatus patientStatus,
            PatientHas patientHas,
            String patientHasOther,
            String postAnesthesiaNote,
            String anesthesiologist,
            String date,
            String time,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.anesthesiaComplications = anesthesiaComplications;
        this.patientStatus = patientStatus;
        this.patientHas = patientHas;
        this.patientHasOther = patientHasOther;
        this.postAnesthesiaNote = postAnesthesiaNote;
        this.anesthesiologist = anesthesiologist;
        this.date = date;
        this.time = time;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
