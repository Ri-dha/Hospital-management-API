package com.azu.hospital.Patients.charts.doctorTour.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assessment_enm.DoctorTourShift;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class DoctorTourDto extends BaseChartsDto {
    private Long chartId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Doctor Tour Chart";

    private String date;

    private String time;

    private Double bmi;
    private Float weight;
    private Float height;

    private String medicalDx;

    private String tourDetails;

    private String followUpNote;

    private Boolean isMaskOn;

    private DoctorTourShift shift;

    private Long createdBy;
    private Long LastModifiedBy;

    private Instant createdAt;
    private Instant updatedAt;

    private String title = "Doctor_Tour_Chart";
    private String link = "edit/tour";


    public DoctorTourDto() {
    }

    public DoctorTourDto(
            Long chartId,
            String chartName,
            String date,
            String time,
            Double bmi,
            Float weight,
            Float height,
            String medicalDx,
            String tourDetails,
            String followUpNote,
            Boolean isMaskOn,
            DoctorTourShift shift,
            Long createdBy,
            Long LastModifiedBy,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.chartId = chartId;
        this.chartName = chartName;
        this.date = date;
        this.time = time;
        this.bmi = bmi;
        this.medicalDx = medicalDx;
        this.tourDetails = tourDetails;
        this.followUpNote = followUpNote;
        this.isMaskOn = isMaskOn;
        this.shift = shift;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
