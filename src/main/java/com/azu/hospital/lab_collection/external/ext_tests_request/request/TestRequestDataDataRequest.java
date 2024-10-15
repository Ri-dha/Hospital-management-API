package com.azu.hospital.lab_collection.external.ext_tests_request.request;

import com.azu.hospital.utils.enums.lab.LabSpots;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TestRequestDataDataRequest {
    @NotNull
    private Long labTestId;

    @NotNull
    private String testName;

    @NotNull
    @Enumerated
    private LabSpots spots;

    @NotNull
    private String note;

    public TestRequestDataDataRequest(Long labTestId,String testName ,LabSpots spots, String note) {
        this.labTestId=labTestId;
        this.testName=testName;
        this.spots = spots;
        this.note = note;
    }

    public TestRequestDataDataRequest() {
    }
}

