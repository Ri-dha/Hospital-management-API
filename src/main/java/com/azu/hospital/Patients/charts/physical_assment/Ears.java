package com.azu.hospital.Patients.charts.physical_assment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ears {
    private Boolean earSymmetrical = false;
    private Boolean earwaxDischarge = false;
    private Boolean earsSkinIntact = false;
    private Boolean lesion = false;
    private Boolean hearingDevices = false;


    @JsonCreator
    public Ears(
            @JsonProperty("earSymmetrical") Boolean earSymmetrical,
            @JsonProperty("earwaxDischarge") Boolean earwaxDischarge,
            @JsonProperty("earsSkinIntact") Boolean earsSkinIntact,
            @JsonProperty("lesion") Boolean lesion,
            @JsonProperty("hearingDevices") Boolean hearingDevices
    ) {
        this.earSymmetrical = earSymmetrical;
        this.earwaxDischarge = earwaxDischarge;
        this.earsSkinIntact = earsSkinIntact;
        this.lesion = lesion;
        this.hearingDevices = hearingDevices;
    }

    public Ears() {
    }
}
