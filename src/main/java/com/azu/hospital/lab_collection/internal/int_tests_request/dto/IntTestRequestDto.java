package com.azu.hospital.lab_collection.internal.int_tests_request.dto;

import com.azu.hospital.lab_collection.internal.int_test_result.dto.IntTestResultDto;
import com.azu.hospital.utils.enums.lab.LabSpots;
import com.azu.hospital.utils.files.File;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class IntTestRequestDto {

    private Long id;
    private String testName;
    private LabSpots spots;
    private String note;
    private String normalValueForTest;
    private List<IntTestResultDto> intTestResults;
    private List<File> testsFiles;
    private Instant createdAt;
    private Instant updatedAt;

    public IntTestRequestDto() {
    }


    public IntTestRequestDto(
            Long id,

            String testName,
            LabSpots spots,
            String note,
            String normalValue,
            List<IntTestResultDto> intTestResults,
            Instant createdAt,
            Instant updatedAt,
            List<File> testsFiles

    ) {
        this.id = id;

        this.testName = testName;
        this.spots = spots;
        this.note = note;
        this.normalValueForTest = normalValue;
        this.intTestResults = intTestResults;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.testsFiles = testsFiles;
    }
}


