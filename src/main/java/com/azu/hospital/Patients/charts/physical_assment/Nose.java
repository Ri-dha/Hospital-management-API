package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Nose {
    private Boolean midline = false;
    private Boolean skinIntact = false;
    private Boolean noseSymmetrical = false;
    private Boolean lesions = false;
    private Boolean congestion = false;
    private Boolean sneezing = false;
    @Column(name = "nose_other")
    private String other;

    public Nose() {
    }

    @JsonCreator
    public Nose(
            @JsonProperty("midline") Boolean midline,
            @JsonProperty("skinIntact") Boolean skinIntact,
            @JsonProperty("noseSymmetrical") Boolean noseSymmetrical,
            @JsonProperty("lesions") Boolean lesions,
            @JsonProperty("congestion") Boolean congestion,
            @JsonProperty("sneezing") Boolean sneezing,
            @JsonProperty("other") String other
    ) {
        this.midline = midline;
        this.skinIntact = skinIntact;
        this.noseSymmetrical = noseSymmetrical;
        this.lesions = lesions;
        this.congestion = congestion;
        this.sneezing = sneezing;
        this.other = other;
    }
}
