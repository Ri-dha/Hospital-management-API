package com.azu.hospital.Patients.charts.nursingAssessment.dto;


import com.azu.hospital.Patients.charts.base_chart.dto.BaseChartsDto;
import com.azu.hospital.Patients.charts.physical_assment.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class NursingAssessmentChartDto extends BaseChartsDto {

    private Long nursingChartId;

    private Long patientId;
    private Long doctorId;
    private String doctorName;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String wardName;
    private String bedNumber;

    private String chartName = "Nursing Assessment Chart";

    private LevelOfConsciousness levelOfConsciousness;

    private Orientation orientation;

    private MentalState mentalState;

    private Eyes eyes;

    private Ears ears;

    private Mouth mouth;

    private Nose nose;

    private Hair hair;

    private Neck neck;

    private Skin skin;

    private Chest chest;

    private Abdomen abdomen;

    private UpperExtremities upperExtremities;

    private LowerExtremities lowerExtremities;

    private String note;

    private BigDecimal painLevel;

    private Long createdBy;
    private Long LastModifiedBy;


    private String title = "NURSING ASSESSMENT";
    private String link = "nursing-assessment";

    public NursingAssessmentChartDto(
            Long nursingChartId,
            String chartName,
            LevelOfConsciousness levelOfConsciousness,
            Orientation orientation,
            MentalState mentalState,
            Eyes eyes,
            Ears ears,
            Mouth mouth,
            Nose nose,
            Hair hair,
            Neck neck,
            Skin skin,
            Chest chest,
            Abdomen abdomen,
            UpperExtremities upperExtremities,
            LowerExtremities lowerExtremities,
            String note,
            BigDecimal painLevel,
            Long createdBy,
            Long LastModifiedBy
    ) {
        this.nursingChartId = nursingChartId;
        this.chartName = chartName;
        this.levelOfConsciousness = levelOfConsciousness;
        this.orientation = orientation;
        this.mentalState = mentalState;
        this.eyes = eyes;
        this.ears = ears;
        this.mouth = mouth;
        this.nose = nose;
        this.hair = hair;
        this.neck = neck;
        this.skin = skin;
        this.chest = chest;
        this.abdomen = abdomen;
        this.upperExtremities = upperExtremities;
        this.lowerExtremities = lowerExtremities;
        this.note = note;
        this.painLevel = painLevel;
        this.createdBy = createdBy;
        this.LastModifiedBy = LastModifiedBy;
    }

    public NursingAssessmentChartDto() {

    }


}
