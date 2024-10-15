package com.azu.hospital.ultrasound.external.dto;

import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalUltrasoundTestDtoMapper implements Function<ExternalUltrasoundTest, ExternalUltrasoundTestDto> {

    public DtoForReturnIdOnly toDtoForId(ExternalUltrasoundTest externalUltrasoundTest){
        return new DtoForReturnIdOnly(
                externalUltrasoundTest.getId()
        );
    }


    public ExternalUltrasoundTestDto apply(ExternalUltrasoundTest externalUltrasoundTest){
        return new ExternalUltrasoundTestDto(
                externalUltrasoundTest.getId(),
                externalUltrasoundTest.getNote(),
                externalUltrasoundTest.getType(),
                externalUltrasoundTest.getState(),
                externalUltrasoundTest.getPatientData(),
                externalUltrasoundTest.getCreatedAt(),
                externalUltrasoundTest.getCreatedBy(),
                externalUltrasoundTest.getLastModifiedBy()
        );
    }

}
