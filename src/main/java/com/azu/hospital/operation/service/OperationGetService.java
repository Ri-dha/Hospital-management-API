package com.azu.hospital.operation.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.Patients.Patient.dto.PatientAllDto;
import com.azu.hospital.Patients.Patient.dto.PatientAllDtoMapper;
import com.azu.hospital.Patients.Patient.dto.PatientDto;
import com.azu.hospital.Patients.Patient.dto.PatientDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.operation.dao.OperationDao;
import com.azu.hospital.operation.dto.OperationDto;
import com.azu.hospital.operation.dto.OperationMapper;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.operation.OperationStates;
import com.azu.hospital.utils.enums.operation.OperationTypes;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

import static com.azu.hospital.utils.humanResourceUtils.HumanResourceUtils.APPOINTMENT_TIME_SUFFIX;

@Service
public class OperationGetService {


    private final OperationDao operationDao;

    private final OperationMapper operationMapper;

    private final PatientDao patientDao;
    private final ExceptionsMessageReturn messageReturn;
    private final PatientDtoMapper patientDtoMapper;


    public OperationGetService(
            @Qualifier("OperationJpa") OperationDao operationDao,
            @Qualifier("OperationMapper") OperationMapper operationMapper,
            @Qualifier("patientRepo") PatientDao patientDao, ExceptionsMessageReturn messageReturn, PatientDtoMapper patientDtoMapper
    ) {
        this.operationDao = operationDao;
        this.operationMapper = operationMapper;
        this.patientDao = patientDao;
        this.messageReturn = messageReturn;
        this.patientDtoMapper = patientDtoMapper;
    }


    public Page<OperationDto> getOperationsByPatientId(Long patientId, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                       messages.getString("patientNotFound") + " " + patientId
                )
        );

        return operationDao.getOperationsByPatientId(patientId, pageable).map(operationMapper);

    }
    public Page<OperationDto> getOperationsByDate(String date , List<OperationStates> states, Pageable pageable) {
        Instant newDate = Instant.parse(date + APPOINTMENT_TIME_SUFFIX);
        if (Objects.isNull(states) || states.size() == 0) states = List.of(OperationStates.values());
        return operationDao.getOperationsByDate(newDate, states, pageable).map(operationMapper);
    }

    public Page<OperationDto> getAllOperationsByState(List<OperationStates> states, Pageable pageable) {
        return operationDao.getAllByListOfStates(states, pageable)
                .map(operationMapper);
    }

    public Page<PatientDto> getAllPatientWhoHasOperationAndStateIsBeforeInOperation(List<OperationStates> states, Pageable pageable) {
        return operationDao.getAllPatientWhoHasOperationAndStateIsBeforeInOperation(states, pageable)
               .map(patientDtoMapper::toDto);
    }
}