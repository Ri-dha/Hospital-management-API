package com.azu.hospital.radiology.internal.dto;

import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InternalRadiologyTestDtoMapper implements Function<InternalRadiologyTest, InternalRadiologyTestDto> {


    @Override
    public InternalRadiologyTestDto apply(InternalRadiologyTest internalRadiologyTest) {
        return new InternalRadiologyTestDto(
                internalRadiologyTest.getId(),
                internalRadiologyTest.getNote(),
                internalRadiologyTest.getType(),
                internalRadiologyTest.getState(),
                internalRadiologyTest.getPatient().getWard() == null ? null :
                        internalRadiologyTest.getPatient().getWard().getName(),
                internalRadiologyTest.getPatient().getBed() == null ? null :
                        internalRadiologyTest.getPatient().getBed().getBedNumber(),
                internalRadiologyTest.getPatient() == null ? null : internalRadiologyTest.getPatient().getId(),
                internalRadiologyTest.getPatient() == null ? null :
                        internalRadiologyTest.getPatient().getPatientData().getFullName(),
                internalRadiologyTest.getNurse() == null ? null : internalRadiologyTest.getNurse().
                        getUsername(),
                internalRadiologyTest.getDoctor() == null ? null : internalRadiologyTest.getDoctor().getUsernameSpecial(),
                internalRadiologyTest.getCreatedAt(),
                internalRadiologyTest.getPatient().lastMedicalHistory() == null ? null :
                        internalRadiologyTest.getPatient().lastMedicalHistory().getDx(),
                internalRadiologyTest.getPatient().lastMedicalHistory() == null ? null :
                        internalRadiologyTest.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
                internalRadiologyTest.getPatient().getPatientData().getGender(),
                internalRadiologyTest.getPatient().getPatientDate().getAge(),
                internalRadiologyTest.getPatient().getPatientData().getWeight(),
                internalRadiologyTest.getPatient().getPatientData().getHeight(),
                internalRadiologyTest.getUploader()== null ? null : internalRadiologyTest.getUploader().getId(),
                internalRadiologyTest.getUploader() == null ? null : internalRadiologyTest.getUploader().getUsernameSpecial(),
                internalRadiologyTest.getAccepter()== null ? null : internalRadiologyTest.getAccepter().getId(),
                internalRadiologyTest.getAccepter() == null ? null : internalRadiologyTest.getAccepter().getUsernameSpecial(),
                internalRadiologyTest.getRejecter()== null ? null : internalRadiologyTest.getRejecter().getId(),
                internalRadiologyTest.getRejecter() == null ? null : internalRadiologyTest.getRejecter().getUsernameSpecial(),
                internalRadiologyTest.getCreatedAt(),
                internalRadiologyTest.getUpdatedAt(),
                internalRadiologyTest.getCreatedBy(),
                internalRadiologyTest.getLastModifiedBy()


        );
    }
}
