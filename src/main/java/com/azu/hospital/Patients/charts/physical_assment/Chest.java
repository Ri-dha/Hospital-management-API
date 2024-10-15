package com.azu.hospital.Patients.charts.physical_assment;


import com.azu.hospital.Patients.charts.physical_assessment_enm.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Chest {
    @Embedded
    private Inspection inspection;

    @Embedded
    private RythmState rythm;

    @Embedded
    private HeartSounds heartSounds;

    private String apicalHeartRate;
    @Embedded
    private BreathSounds breathSounds;

    @Embedded
    private RespiratoryRythm respiratoryRythm;

    @Embedded
    private Depth depth;


    public Chest() {
    }

    @JsonCreator
    public Chest(
            @JsonProperty("inspection") Inspection inspection,
            @JsonProperty("rythm") RythmState rythm,
            @JsonProperty("heartSounds") HeartSounds heartSounds,
            @JsonProperty("apicalHeartRate") String apicalHeartRate,
            @JsonProperty("breathSounds") BreathSounds breathSounds,
            @JsonProperty("respiratoryRythm") RespiratoryRythm respiratoryRythm,
            @JsonProperty("depth") Depth depth
    ) {
        this.inspection = inspection;
        this.rythm = rythm;
        this.heartSounds = heartSounds;
        this.apicalHeartRate = apicalHeartRate;
        this.breathSounds = breathSounds;
        this.respiratoryRythm = respiratoryRythm;
        this.depth = depth;
    }
}
