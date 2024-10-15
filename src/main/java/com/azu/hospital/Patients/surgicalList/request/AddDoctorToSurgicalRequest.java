package com.azu.hospital.Patients.surgicalList.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AddDoctorToSurgicalRequest {


    private List<Long> doctorIds;

    private Long surgicalId;

    @JsonCreator
    public AddDoctorToSurgicalRequest(
            @JsonProperty("doctorIds") List<Long> doctorIds,
            @JsonProperty("surgicalId") Long surgicalId
    ) {
        this.doctorIds = doctorIds;
        this.surgicalId = surgicalId;
    }

    public AddDoctorToSurgicalRequest() {
    }
}
