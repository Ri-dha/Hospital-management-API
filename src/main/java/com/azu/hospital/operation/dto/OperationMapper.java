package com.azu.hospital.operation.dto;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.operation.entity.Operation;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service("OperationMapper")

public class OperationMapper implements Function<Operation,OperationDto> {


    @Override
    public OperationDto apply(Operation operation) {
        return new OperationDto(
                operation.getId(),
                operation.getPatient().getId(),
                operation.getRequestNote(),
                operation.getDoneNote(),
                operation.getPatient().getPatientData().getFullName(),
                operation.getPatient().getDoctor().stream().findFirst().map(doctor -> doctor.getDoctor().getUsernameSpecial()).orElse(null),
                operation.getUploader() == null ? null : operation.getUploader().getId(),
                operation.getUploader() == null ? null : operation.getUploader().getUsernameSpecial(),
                operation.getUploader() == null ? null : operation.getUploader().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                operation.getAccepter() == null ? null : operation.getAccepter().getId(),
                operation.getAccepter() == null ? null : operation.getAccepter().getUsernameSpecial(),
                operation.getAccepter() == null ? null : operation.getAccepter().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                operation.getRejecter() == null ? null : operation.getRejecter().getId(),
                operation.getRejecter() == null ? null : operation.getRejecter().getUsernameSpecial(),
                operation.getRejecter() == null ? null : operation.getRejecter().getRoles().stream().map(Role::getName).collect(Collectors.toList()),
                operation.getPatient().getPatientContact().getMobile(),
                operation.getOperationDate(),
                operation.getPatient().getBed().getId(),
                operation.getPatient().getBed().getBedNumber(),
                operation.getPatient().getPatientData().getGender(),
                operation.getPatient().getPatientDate().getAge(),
                operation.getPatient().getPatientData().getWeight(),
                operation.getPatient().getPatientData().getHeight(),
                operation.getPatient().getWard().getWardId(),
                operation.getPatient().getWard().getName(),
                operation.getPatient().lastMedicalHistory().getDx(),
                operation.getPatient().lastMedicalHistory().getDrugHistoryAllergy(),
                operation.getSurgical().getSurgicalName(),
                operation.getAnesthetization() ,
                operation.getPatient().lastMedicalHistory().getTriage(),
                operation.getTriage(),
                operation.getState(),
                operation.getCreatedAt(),
                operation.getUpdatedAt(),
                operation.getOperationDate()

        );
    }
}

