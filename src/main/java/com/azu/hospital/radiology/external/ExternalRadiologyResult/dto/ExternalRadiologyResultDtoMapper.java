package com.azu.hospital.radiology.external.ExternalRadiologyResult.dto;

import com.azu.hospital.radiology.external.ExternalRadiologyResult.entity.ExternalRadiologyResult;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalRadiologyResultDtoMapper implements Function<ExternalRadiologyResult, ExternalRadiologyResultDto> {


    @Override
    public ExternalRadiologyResultDto apply(ExternalRadiologyResult externalRadiologyResult) {
        return new ExternalRadiologyResultDto(
                externalRadiologyResult.getId(),
                externalRadiologyResult.getNote(),
                externalRadiologyResult.getExternalRadiologyTest().getId(),
                externalRadiologyResult.getFiles() == null ? null : externalRadiologyResult.getFiles(),
                externalRadiologyResult.getExternalRadiologyTest().getType() == null ? null : externalRadiologyResult.getExternalRadiologyTest().getType()
        );
    }
}
