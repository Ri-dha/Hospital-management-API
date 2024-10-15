package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MentalState {
    private Boolean calm = false;
    private Boolean cooperative = false;
    private Boolean uncooperative = false;
    private Boolean pleasant = false;
    private Boolean anxious = false;
    private Boolean angry = false;
    private Boolean withdrawn = false;


    public MentalState() {
    }

    @JsonCreator
    public MentalState(
            @JsonProperty("calm") Boolean calm,
            @JsonProperty("cooperative") Boolean cooperative,
            @JsonProperty("uncooperative") Boolean uncooperative,
            @JsonProperty("pleasant") Boolean pleasant,
            @JsonProperty("anxious") Boolean anxious,
            @JsonProperty("angry") Boolean angry,
            @JsonProperty("withdrawn") Boolean withdrawn
    ) {
        this.calm = calm;
        this.cooperative = cooperative;
        this.uncooperative = uncooperative;
        this.pleasant = pleasant;
        this.anxious = anxious;
        this.angry = angry;
        this.withdrawn = withdrawn;
    }
}
