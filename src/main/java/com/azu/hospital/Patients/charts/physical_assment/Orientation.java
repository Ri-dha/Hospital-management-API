package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Orientation {
    private Boolean person;
    private String time;
    private Boolean place;
    private Boolean situation;

    public Orientation() {
    }

    @JsonCreator
    public Orientation(
            @JsonProperty("person") Boolean person,
            @JsonProperty("time") String time,
            @JsonProperty("place") Boolean place,
            @JsonProperty("situation") Boolean situation
    ) {
        this.person = person;
        this.time = time;
        this.place = place;
        this.situation = situation;
    }
}
