package com.azu.hospital.lab_collection.internal.int_tests_request.dto;

import com.azu.hospital.lab_collection.internal.int_test_result.dto.IntTestResultDtoMapper;
import com.azu.hospital.lab_collection.internal.int_tests_request.entity.IntTestRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class IntTestRequestDtoMapper {
    private final IntTestResultDtoMapper intTestResultDtoMapper;

    public IntTestRequestDtoMapper(IntTestResultDtoMapper intTestResultDtoMapper) {
        this.intTestResultDtoMapper = intTestResultDtoMapper;
    }

    public IntTestRequestDto toDto(IntTestRequest testRequest) {
        String normalValue = null;
        if (testRequest.getTestNameForMainTest() != null) {
            normalValue = testRequest.getTestNameForMainTest().getNormalValue();
        }
        return new IntTestRequestDto(
                testRequest.getId(),
                testRequest.getTestName(),
                testRequest.getSpots(),
                testRequest.getNote(),
                 normalValue,
                testRequest.getIntTestResults().stream()
                        .map(intTestResultDtoMapper::toDto)
                        .collect(Collectors.toList()),
                testRequest.getCreatedAt(),
                testRequest.getUpdatedAt(),
                testRequest.getTestsFiles()
        );
    }
}
