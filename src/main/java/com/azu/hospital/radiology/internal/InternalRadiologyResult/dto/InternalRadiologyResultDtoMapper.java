package com.azu.hospital.radiology.internal.InternalRadiologyResult.dto;

import com.azu.hospital.radiology.internal.InternalRadiologyResult.entity.InternalRadiologyResult;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InternalRadiologyResultDtoMapper implements Function<InternalRadiologyResult, InternalRadiologyResultDto> {

    @Override
    public InternalRadiologyResultDto apply(InternalRadiologyResult internalRadiologyResult) {
        return new InternalRadiologyResultDto(
                internalRadiologyResult.getId(),
                internalRadiologyResult.getNote(),
                internalRadiologyResult.getInternalRadiologyTest().getId(),
                internalRadiologyResult.getInternalRadiologyTest().getType(),
                internalRadiologyResult.getFiles() == null ? null : internalRadiologyResult.getFiles(),
                internalRadiologyResult.getCreatedAt(),
                internalRadiologyResult.getUpdatedAt()
        );
    }
}
