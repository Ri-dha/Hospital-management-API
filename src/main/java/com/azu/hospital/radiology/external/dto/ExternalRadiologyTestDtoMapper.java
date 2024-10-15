package com.azu.hospital.radiology.external.dto;

import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.radiology.external.entity.ExternalRadiologyTest;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalRadiologyTestDtoMapper implements Function<ExternalRadiologyTest, ExternalRadiologyTestDto> {


    public DtoForReturnIdOnly toDtoForId(ExternalRadiologyTest externalRadiologyTest){
        return new DtoForReturnIdOnly(
                externalRadiologyTest.getId()
        );
    }
    @Override
    public ExternalRadiologyTestDto apply(ExternalRadiologyTest externalRadiologyTest) {
        return new ExternalRadiologyTestDto(
                externalRadiologyTest.getId(),
                externalRadiologyTest.getNote(),
                externalRadiologyTest.getType(),
                externalRadiologyTest.getState(),
                externalRadiologyTest.getPatientData(),
                externalRadiologyTest.getCreatedAt(),
                externalRadiologyTest.getCreatedBy(),
                externalRadiologyTest.getLastModifiedBy()
        );
    }
}
