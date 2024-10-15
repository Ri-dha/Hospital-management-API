package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Abdomen {
    @Embedded
    private AbdomenInspection abdomenInspection;

    @Embedded
    private BowelSounds bowelSounds;

    @Embedded
    private Palpation palpation;

    public Abdomen() {
    }

    @JsonCreator
    public Abdomen(
            @JsonProperty("abdomenInspection") AbdomenInspection abdomenInspection,
            @JsonProperty("bowelSounds") BowelSounds bowelSounds,
            @JsonProperty("palpation") Palpation palpation
    ) {
        this.abdomenInspection = abdomenInspection;
        this.bowelSounds = bowelSounds;
        this.palpation = palpation;
    }


}
