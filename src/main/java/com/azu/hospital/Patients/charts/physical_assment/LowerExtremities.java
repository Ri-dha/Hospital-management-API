package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class LowerExtremities {
    @Embedded
    private LowerExtremitiesStates pedalPush;
    private String edema;
    private String lowerRageOfMotion;
    private String capillaryRefillTime;
    private String dorsalisPedisPulse;
    private String posteriorTibialPulse;

    public LowerExtremities() {
    }

    @JsonCreator
    public LowerExtremities(
            @JsonProperty("pedalPush") LowerExtremitiesStates pedalPush,
            @JsonProperty("edema") String edema,
            @JsonProperty("lowerRageOfMotion") String lowerRageOfMotion,
            @JsonProperty("capillaryRefillTime") String capillaryRefillTime,
            @JsonProperty("dorsalisPedisPulse") String dorsalisPedisPulse,
            @JsonProperty("posteriorTibialPulse") String posteriorTibialPulse
    ) {
        this.pedalPush = pedalPush;
        this.edema = edema;
        this.lowerRageOfMotion = lowerRageOfMotion;
        this.capillaryRefillTime = capillaryRefillTime;
        this.dorsalisPedisPulse = dorsalisPedisPulse;
        this.posteriorTibialPulse = posteriorTibialPulse;
    }
}
