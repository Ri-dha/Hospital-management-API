package com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.dto;

import com.azu.hospital.ultrasound.external.ExternalUltrasoundResult.entity.ExternalUltrasoundResult;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalUltrasoundResultDtoMapper implements Function<ExternalUltrasoundResult, ExternalUltrasoundResultDto> {

    @Override
    public ExternalUltrasoundResultDto apply(ExternalUltrasoundResult externalUltrasoundResult){
        return new ExternalUltrasoundResultDto(
                externalUltrasoundResult.getId(),
                externalUltrasoundResult.getNote(),
                externalUltrasoundResult.getExternalUltrasoundTest().getId(),
                externalUltrasoundResult.getFiles() == null ? null : externalUltrasoundResult.getFiles()
        );
    }
}
