package com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.dto;

import com.azu.hospital.ultrasound.internal.InternalUltrasoundResult.entity.InternalUltrasoundResult;
import org.springframework.stereotype.Service;

@Service
public class InternalUltrasoundResultDtoMapper {

    public InternalUltrasoundResultDto toDto(InternalUltrasoundResult internalUltrasoundResult){
        return new InternalUltrasoundResultDto(
                internalUltrasoundResult.getId(),
                internalUltrasoundResult.getNote(),
                internalUltrasoundResult.getInternalUltrasoundTest().getId(),
                internalUltrasoundResult.getFiles() == null ? null : internalUltrasoundResult.getFiles()
        );
    }
}
