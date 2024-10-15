package com.azu.hospital.Patients.charts.operativeNote.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class OperativeNoteDto extends BaseChartsDto {

    private Long operativeNoteId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Operative Note Chart";

    private String operativeNote;

    private String operationName;

    private Long createdBy;
    private Long LastModifiedBy;

    private String title = "OPERATIVE NOTE";
    private String link = "operative-note";

    public OperativeNoteDto() {
    }

    public OperativeNoteDto(Long operativeNoteId, Long patientId, Long doctorId, String doctorName, String patientName, String patientAge, String patientGender, String wardName, String bedNumber, String chartName, String operativeNote, String operationName, String title, String link) {
        this.operativeNoteId = operativeNoteId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.wardName = wardName;
        this.bedNumber = bedNumber;
        this.chartName = chartName;
        this.operativeNote = operativeNote;
        this.operationName = operationName;
        this.title = title;
        this.link = link;
    }

    public OperativeNoteDto(Long operativeNoteId, String chartName, String operativeNote, String operationName
            , Long createdBy, Long LastModifiedBy) {
        this.operativeNoteId = operativeNoteId;
        this.chartName = chartName;
        this.operativeNote = operativeNote;
        this.operationName = operationName;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }
}
