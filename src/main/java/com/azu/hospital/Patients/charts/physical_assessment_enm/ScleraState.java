package com.azu.hospital.Patients.charts.physical_assessment_enm;

import lombok.Data;

@Data
public class ScleraState {
    private Boolean WHITE = false;
    private Boolean INTACT = false;
    private String OTHER ;

    public ScleraState() {
    }

    public ScleraState(Boolean WHITE, Boolean INTACT, String OTHER) {
        this.WHITE = WHITE;
        this.INTACT = INTACT;
        this.OTHER = OTHER;
    }
}
