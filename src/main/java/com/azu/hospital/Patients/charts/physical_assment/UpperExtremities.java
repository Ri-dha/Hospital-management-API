package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class UpperExtremities {
    @Embedded
    private UpperExtremitiesStates shoulderShrug;

    private String gripStrength;

    private String rageOfMotion;

    private String capillaryRefill;

    private String radialPulse;

    public UpperExtremities() {
    }

    @JsonCreator
    public UpperExtremities(
            @JsonProperty("shoulderShrug") UpperExtremitiesStates shoulderShrug,
            @JsonProperty("gripStrength") String gripStrength,
            @JsonProperty("rageOfMotion") String rageOfMotion,
            @JsonProperty("capillaryRefill") String capillaryRefill,
            @JsonProperty("radialPulse") String radialPulse
    ) {
        this.shoulderShrug = shoulderShrug;
        this.gripStrength = gripStrength;
        this.rageOfMotion = rageOfMotion;
        this.capillaryRefill = capillaryRefill;
        this.radialPulse = radialPulse;
    }
}
