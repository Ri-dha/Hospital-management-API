package com.azu.hospital.radiology.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDtoMapper;
import com.azu.hospital.security.newsecurity.config.JwtService;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.Generic.GenericBaseService;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class InternalRadiologyTestGetService extends GenericBaseService {
    private final InternalRadiologyTestDao internalRadiologyTestDao;
    private final ExceptionsMessageReturn messageReturn;
    private final PatientDao patientDao;
    private final InternalRadiologyTestDtoMapper mapper;

    public InternalRadiologyTestGetService(
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            JwtService jwtService,
            HttpServletRequest request, ExceptionsMessageReturn messageReturn,
            InternalRadiologyTestDtoMapper mapper
    ) {
        super(jwtService, request);
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.patientDao = patientDao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
    }


    public Page<InternalRadiologyTestDto> getAllTests(
            List<RadiologyTypeEnum> types,
            List<RadiologyOrderState> states,
            String search,
            Pageable pageable
    ) {
        if (states == null || states.isEmpty())
            states = Arrays.stream(RadiologyOrderState.values()).toList();

        if (types == null || types.isEmpty())
            types = Arrays.stream(RadiologyTypeEnum.values()).toList();

        return internalRadiologyTestDao.getAllByFilter(types, search, states, pageable).map(mapper);
    }


    public InternalRadiologyTestDto getById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return mapper.apply(internalRadiologyTestDao.getInternalRadiologyTestById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("InternalRadiologyTestNotFound") + " " + id
                )
        ));
    }


    public Page<InternalRadiologyTestDto> getInternalRadiologyByPatient(
            Long patientId, List<RadiologyTypeEnum> types, List<RadiologyOrderState> states, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                )
        );
        return internalRadiologyTestDao.getByPatientId(patientId, types, states, pageable).map(mapper);
    }


    public List<InternalRadiologyTestDto> getAllByPatientIdAndState(Long patientId, List<RadiologyOrderState> states) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        if (states == null || states.isEmpty())
            states = Arrays.stream(RadiologyOrderState.values()).toList();

        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + patientId
                )
        );
        return internalRadiologyTestDao.getAllCompletedByPatientId(patientId, states)
                .stream()
                .map(mapper)
                .toList();
    }


}
