package com.azu.hospital.lab_collection.internal.int_test_result.dto;

import com.azu.hospital.lab_collection.internal.int_test_result.entity.IntTestResult;
import org.springframework.stereotype.Service;

@Service
public class IntTestResultDtoMapper {
    public IntTestResultDto toDto(IntTestResult intTestResult){
        return new IntTestResultDto(

                intTestResult.getResultName(),
                intTestResult.getIntTestRequest().getTestName(),
                intTestResult.getTestResult(),
                intTestResult.getNormalValue()

        );
    }
}
