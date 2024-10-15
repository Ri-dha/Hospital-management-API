package com.azu.hospital.lab_collection.external.ext_tests_request.dto;

import com.azu.hospital.lab_collection.external.ext_test_result.dto.ExtTestResultDto;
import com.azu.hospital.utils.enums.lab.LabSpots;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ExtTestRequestDto {

    private Long id;

    private String testName;

    private LabSpots spots;

    private String note;

    private Instant createdAt;

    private Instant updatedAt;

    private List<ExtTestResultDto> extTestResultDtoList;

    public ExtTestRequestDto() {
    }

    public ExtTestRequestDto(Long id, String testName, LabSpots spots, String note, Instant createdAt, Instant updatedAt, List<ExtTestResultDto> extTestResultDtoList) {
        this.id = id;
        this.testName = testName;
        this.spots = spots;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.extTestResultDtoList = extTestResultDtoList;
    }
}
