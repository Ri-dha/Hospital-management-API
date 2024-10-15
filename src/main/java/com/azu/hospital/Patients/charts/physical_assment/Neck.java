package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Neck {
    private Boolean symmetry = false;
    private Boolean roundConvex = false;
    private Boolean rangeOfMotion = false;
    private Boolean jugularVeinDistension = false;
    private Boolean lumpsBumps = false;

    public Neck() {
    }

    @JsonCreator
    public Neck(
            @JsonProperty("symmetry") Boolean symmetry,
            @JsonProperty("roundConvex") Boolean roundConvex,
            @JsonProperty("rangeOfMotion") Boolean rangeOfMotion,
            @JsonProperty("jugularVeinDistension") Boolean jugularVeinDistension,
            @JsonProperty("lumpsBumps") Boolean lumpsBumps
    ) {
        this.symmetry = symmetry;
        this.roundConvex = roundConvex;
        this.rangeOfMotion = rangeOfMotion;
        this.jugularVeinDistension = jugularVeinDistension;
        this.lumpsBumps = lumpsBumps;
    }
}
