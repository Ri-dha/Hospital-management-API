package com.azu.hospital.lab_collection.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDto;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDtoMapper;
import com.azu.hospital.lab_collection.internal.dto.InternalLabWithRequestDto;
import com.azu.hospital.lab_collection.internal.dto.InternalLabWithRequestDtoMapper;
import com.azu.hospital.lab_collection.internal.int_tests_request.dao.IntTestRequestDao;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class InternalLabGetService extends GenericBaseService {

    private final InternalLabDao internalLabDao;

    private final InternalLabDtoMapper mapper;

    private final InternalLabWithRequestDtoMapper internalLabWithRequestDtoMapper;
    private final PatientDao patientDao;
    private final ExceptionsMessageReturn messageReturn;

    private final IntTestRequestDao intTestRequestDao;

    public InternalLabGetService(
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("internalLabDtoMapper") InternalLabDtoMapper mapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            IntTestRequestDao intTestRequestDao,
            JwtService jwtService,
            HttpServletRequest httpServletRequest,
            InternalLabWithRequestDtoMapper internalLabWithRequestDtoMapper, ExceptionsMessageReturn messageReturn) {
        super(jwtService, httpServletRequest);
        this.internalLabDao = internalLabDao;
        this.mapper = mapper;
        this.patientDao = patientDao;
        this.intTestRequestDao = intTestRequestDao;

        this.internalLabWithRequestDtoMapper = internalLabWithRequestDtoMapper;
        this.messageReturn = messageReturn;
    }


    public Page<InternalLabDto> getInternalLabByState(Pageable pageable, List<InternalLabRequestStatusEnum> states) {
        return internalLabDao.getAllByState(states, pageable).map(mapper);
    }


    public Page<InternalLabDto> getByPatientId(Long patientId, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                )
        );

        return internalLabDao.getAllByPatientId(patientId, pageable).map(mapper);
    }

    public Optional<InternalLabWithRequestDto> getInternalLabByRequestId(Long labId) {
        return internalLabDao.getInternalLabByRequestId(labId)
                .map(internalLabWithRequestDtoMapper);
    }

}
