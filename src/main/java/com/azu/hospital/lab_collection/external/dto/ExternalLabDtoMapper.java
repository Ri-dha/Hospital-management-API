package com.azu.hospital.lab_collection.external.dto;

import com.azu.hospital.lab_collection.external.entity.ExternalLabTest;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExternalLabDtoMapper implements Function<ExternalLabTest, ExternalLabDto> {


    @Override
    public ExternalLabDto apply(ExternalLabTest externalLabTest) {
        return new ExternalLabDto(
                externalLabTest.getId(),
                externalLabTest.getPatientName(),
                externalLabTest.getDoctorName(),
                externalLabTest.getGender(),
                externalLabTest.getAge(),
                externalLabTest.getAdmissionDate(),
                externalLabTest.getState().getName(),
                externalLabTest.getDx(),
                externalLabTest.getAllergy(),
                externalLabTest.getNote() == null ? null : externalLabTest.getNote(),
                externalLabTest.getHeight() == null ? null : externalLabTest.getHeight(),
                externalLabTest.getWeight() == null ? null : externalLabTest.getWeight(),
                externalLabTest.getCreatedBy(),
                externalLabTest.getLastModifiedBy()
        );
    }
}
