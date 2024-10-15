package com.azu.hospital.lab_collection.external.ext_test_result.dto;

import com.azu.hospital.lab_collection.external.ext_test_result.entity.ExtTestResult;
import org.springframework.stereotype.Service;

@Service
public class ExtTestResultDtoMapper {
    public ExtTestResultDto toDto(ExtTestResult extTestResult){
        return new ExtTestResultDto(
                extTestResult.getResultName(),
                extTestResult.getTestResult(),
                extTestResult.getNormalValue()
        );
    }
}
