package com.azu.hospital.Patients.charts.neurologicalObservation.dto;

import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.AdultEntity;
import com.azu.hospital.Patients.charts.neurologicalObservation.entities.ChildEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NeurologicalObservationDto extends BaseChartsDto {
    private Long id;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Neurological Observation Chart";

    private String date;

    private String candidateName;

    private Long createdBy;
    private Long LastModifiedBy;

    @JsonIgnoreProperties("chart")
    private AdultEntity adult;


    @JsonIgnoreProperties("chart")
    private ChildEntity child;


    private String note;
    private String title = "Neurological Observation";
    private String link = "neurological";

    public NeurologicalObservationDto() {
    }

    public NeurologicalObservationDto(
            Long id,
            String chartName,
            String date,
            String candidateName,
            AdultEntity adult,
            ChildEntity child,
            String note,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.id = id;
        this.chartName = chartName;
        this.date = date;
        this.candidateName = candidateName;
        this.adult = adult;
        this.child = child;
        this.note = note;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;

    }
}
