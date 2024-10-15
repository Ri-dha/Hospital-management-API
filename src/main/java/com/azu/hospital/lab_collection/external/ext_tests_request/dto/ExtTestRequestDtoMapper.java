package com.azu.hospital.lab_collection.external.ext_tests_request.dto;

import com.azu.hospital.lab_collection.external.ext_test_result.dto.ExtTestResultDtoMapper;
import com.azu.hospital.lab_collection.external.ext_tests_request.entity.ExtTestRequest;
import org.springframework.stereotype.Service;

@Service
public class ExtTestRequestDtoMapper {
    public final ExtTestResultDtoMapper extTestResultDtoMapper;

    public ExtTestRequestDtoMapper(ExtTestResultDtoMapper extTestResultDtoMapper) {
        this.extTestResultDtoMapper = extTestResultDtoMapper;
    }

    public ExtTestRequestDto toDto(ExtTestRequest testRequest) {
        return new ExtTestRequestDto(
                testRequest.getId(),
                testRequest.getTestName(),
                testRequest.getSpots() ,
                testRequest.getNote(),
                testRequest.getCreatedAt(),
                testRequest.getUpdatedAt(),
                testRequest.getExtTestRequest()
                        .stream()
                        .map(extTestResultDtoMapper::toDto)
                        .toList()

        );
    }
}
