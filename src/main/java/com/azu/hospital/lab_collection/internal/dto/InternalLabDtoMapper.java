package com.azu.hospital.lab_collection.internal.dto;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InternalLabDtoMapper implements Function<InternalLabTest, InternalLabDto> {


    @Override
    public InternalLabDto apply(InternalLabTest internalLabTest) {
        return new InternalLabDto(
                internalLabTest.getId(),
                internalLabTest.getCreatedAt(),
                internalLabTest.getState().getName(),
                internalLabTest.getNote() == null ? null : internalLabTest.getNote(),
                internalLabTest.getUploader() == null ? null : internalLabTest.getUploader().getId(),
                internalLabTest.getUploader() == null ? null : internalLabTest.getUploader().getUsernameSpecial(),
                internalLabTest.getUploader() == null ? null :
                        internalLabTest.getUploader().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                internalLabTest.getAccepter() == null ? null : internalLabTest.getAccepter().getId(),
                internalLabTest.getAccepter() == null ? null : internalLabTest.getAccepter().getUsernameSpecial(),
                internalLabTest.getAccepter() == null ? null : internalLabTest.getAccepter().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                internalLabTest.getRejecter() == null ? null : internalLabTest.getRejecter().getId(),
                internalLabTest.getRejecter() == null ? null : internalLabTest.getRejecter().getUsernameSpecial(),
                internalLabTest.getRejecter() == null ? null : internalLabTest.getRejecter().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                internalLabTest.getPatient().getId(),
                internalLabTest.getPatient().getPatientData().getFullName(),
                internalLabTest.getPatient().getPatientData().getGender(),
                internalLabTest.getPatient().getWard().getName(),
                internalLabTest.getPatient().getPatientData().getWeight(),
                internalLabTest.getPatient().getPatientData().getHeight(),
                internalLabTest.getPatient().getBed().getBedNumber(),
                internalLabTest.getPatient().getPatientDate().getAge(),
                internalLabTest.getCreatedBy(),
                internalLabTest.getLastModifiedBy()


        );
    }
}
