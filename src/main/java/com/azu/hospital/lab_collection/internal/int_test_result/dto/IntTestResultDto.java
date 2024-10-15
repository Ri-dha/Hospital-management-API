package com.azu.hospital.lab_collection.internal.int_test_result.dto;

import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IntTestResultDto {
    private String resultName;
    private String testName;
    private String testResult;
    private String normalValue;



    public IntTestResultDto() {
    }

    public IntTestResultDto(
            String resultName,
            String testName,
            String testResult,
            String normalValue
    ) {

        this.resultName = resultName;
        this.testName = testName;
        this.testResult = testResult;
        this.normalValue = normalValue;



    }


}
