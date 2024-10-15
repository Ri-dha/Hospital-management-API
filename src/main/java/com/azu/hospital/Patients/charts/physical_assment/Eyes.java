package com.azu.hospital.Patients.charts.physical_assment;

import com.azu.hospital.Patients.charts.physical_assessment_enm.ScleraState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class Eyes {
    @Embedded
    private Conjunctiva conjunctiva;

    @Embedded
    private Pupils pupils;

    @Embedded
    private ScleraState sclera;

    private Boolean abnormalDischarged;
    @Embedded
    private AbnormalEyeFinding abnormalFinding;


    public Eyes() {
    }

    @JsonCreator
    public Eyes(
            @JsonProperty("conjunctiva") Conjunctiva conjunctiva,
            @JsonProperty("pupils") Pupils pupils,
            @JsonProperty("sclera") ScleraState sclera,
            @JsonProperty("abnormalDischarged")Boolean abnormalDischarged,
            @JsonProperty("abnormalFinding") AbnormalEyeFinding abnormalFinding
    ) {
        this.conjunctiva = conjunctiva;
        this.pupils = pupils;
        this.sclera = sclera;
        this.abnormalDischarged = abnormalDischarged;
        this.abnormalFinding = abnormalFinding;
    }
}
