package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Mouth {
    @Column(name = "mouth_moist")
    private Boolean moist = false;

    @Column(name = "mouth_dry")
    private Boolean dry = false;

    @Column(name = "mouth_pink")
    private Boolean pink = false;

    @Column(name = "mouth_sores")
    private Boolean sores = false;

    @Column(name = "mouth_other")
    private String other;


    public Mouth() {
    }

    @JsonCreator
    public Mouth(
            @JsonProperty("moist") Boolean moist,
            @JsonProperty("dry") Boolean dry,
            @JsonProperty("pink") Boolean pink,
            @JsonProperty("sores") Boolean sores,
            @JsonProperty("other") String other
    ) {
        this.moist = moist;
        this.dry = dry;
        this.pink = pink;
        this.sores = sores;
        this.other = other;
    }
}
