package com.azu.hospital.ultrasound.internal.dto;

import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InternalUltrasoundTestDtoMapper implements Function<InternalUltrasoundTest,InternalUltrasoundTestDto> {

    @Override
    public InternalUltrasoundTestDto apply(InternalUltrasoundTest internalUltrasoundTest) {
        return new InternalUltrasoundTestDto(
                internalUltrasoundTest.getId(),
                internalUltrasoundTest.getNote(),
                internalUltrasoundTest.getType(),
                internalUltrasoundTest.getState(),
                internalUltrasoundTest.getPatient().getWard() == null ? null :
                        internalUltrasoundTest.getPatient().getWard().getName(),
                internalUltrasoundTest.getPatient().getBed() == null ? null :
                        internalUltrasoundTest.getPatient().getBed().getBedNumber(),
                internalUltrasoundTest.getPatient() == null ? null : internalUltrasoundTest.getPatient().getId(),
                internalUltrasoundTest.getPatient() == null ? null :
                        internalUltrasoundTest.getPatient().getPatientData().getFullName(),
                internalUltrasoundTest.getNurse() == null ? null : internalUltrasoundTest.getNurse().
                        getUsername(),
                internalUltrasoundTest.getCreatedAt(),
                internalUltrasoundTest.getPatient().lastMedicalHistory() == null ? null :
                        internalUltrasoundTest.getPatient().lastMedicalHistory().getDx(),
                internalUltrasoundTest.getPatient().lastMedicalHistory() == null ? null :
                        internalUltrasoundTest.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
                internalUltrasoundTest.getPatient().getPatientData().getGender(),
                internalUltrasoundTest.getPatient().getPatientDate().getAge(),
                internalUltrasoundTest.getPatient().getPatientData().getWeight(),
                internalUltrasoundTest.getPatient().getPatientData().getHeight(),
                internalUltrasoundTest.getUploader() == null ? null : internalUltrasoundTest.getUploader().getId(),
                internalUltrasoundTest.getUploader() == null ? null : internalUltrasoundTest.getUploader().getUsernameSpecial(),
                internalUltrasoundTest.getAccepter() == null ? null : internalUltrasoundTest.getAccepter().getId(),
                internalUltrasoundTest.getAccepter() == null ? null : internalUltrasoundTest.getAccepter().getUsernameSpecial(),
                internalUltrasoundTest.getRejecter() == null ? null : internalUltrasoundTest.getRejecter().getId(),
                internalUltrasoundTest.getRejecter() == null ? null : internalUltrasoundTest.getRejecter().getUsernameSpecial(),
                internalUltrasoundTest.getCreatedBy(),
                internalUltrasoundTest.getLastModifiedBy()
        );
    }
}