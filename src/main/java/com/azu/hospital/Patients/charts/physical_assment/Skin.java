package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Skin {
    @Embedded
    private Temperature temperature;

    @Embedded
    private Moisture moisture;

    @Embedded
    private Turgor turgor;

    private String color;

    private String wound;

    private String pressureUlcer;

    public Skin() {
    }

    @JsonCreator
    public Skin(
            @JsonProperty("temperature") Temperature temperature,
            @JsonProperty("moisture") Moisture moisture,
            @JsonProperty("turgor") Turgor turgor,
            @JsonProperty("color") String color,
            @JsonProperty("wound") String wound,
            @JsonProperty("pressureUlcer") String pressureUlcer
    ) {
        this.temperature = temperature;
        this.moisture = moisture;
        this.turgor = turgor;
        this.color = color;
        this.wound = wound;
        this.pressureUlcer = pressureUlcer;
    }
}
