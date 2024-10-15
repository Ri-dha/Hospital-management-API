package com.azu.hospital.Patients.charts.nursingAssessment.request;

import com.azu.hospital.Patients.charts.physical_assment.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class NursingAssessmentChartRequest {
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
    @Min(1)
    @Max(10)
    private BigDecimal painLevel;

    @JsonCreator
    public NursingAssessmentChartRequest(
            @JsonProperty("levelOfConsciousness") LevelOfConsciousness levelOfConsciousness,
            @JsonProperty("orientation") Orientation orientation,
            @JsonProperty("mentalState") MentalState mentalState,
            @JsonProperty("eyes") Eyes eyes,
            @JsonProperty("ears") Ears ears,
            @JsonProperty("mouth") Mouth mouth,
            @JsonProperty("nose") Nose nose,
            @JsonProperty("hair") Hair hair,
            @JsonProperty("neck") Neck neck,
            @JsonProperty("skin") Skin skin,
            @JsonProperty("chest") Chest chest,
            @JsonProperty("abdomen") Abdomen abdomen,
            @JsonProperty("upperExtremities") UpperExtremities upperExtremities,
            @JsonProperty("lowerExtremities") LowerExtremities lowerExtremities,
            @JsonProperty("note") String note,
            @JsonProperty("painLevel") BigDecimal painLevel

    ) {
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
    }
}
