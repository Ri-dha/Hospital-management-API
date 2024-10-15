package com.azu.hospital.lab_collection.external.ext_test_result.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtTestResultDto {


    private String resultName;

    private String testResult;

    private String normalValue;


    public ExtTestResultDto() {
    }

    public ExtTestResultDto(
            String resultName,
            String testResult,
            String normalValue
    ) {
        this.resultName = resultName;

        this.testResult = testResult;
        this.normalValue = normalValue;

    }
}
