package com.azu.hospital.ultrasound.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class InternalUltrasoundTestGetService {

    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final PatientDao patientDao;
    private final InternalUltrasoundTestDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    public InternalUltrasoundTestGetService(
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("patientRepo") PatientDao patientDao,
            InternalUltrasoundTestDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public Page<InternalUltrasoundTestDto> getAllTests(
            List<UltrasoundTypeEnum> types,
            List<UltrasoundOrderState> states,
            String search,
            Pageable pageable) {

        return internalUltrasoundTestDao.getAllByFilter(types,  states, search, pageable)
                .map(mapper);

    }

    public InternalUltrasoundTestDto getById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        InternalUltrasoundTest internalUltrasoundTest = internalUltrasoundTestDao.findById(id).orElseThrow(

                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        return mapper.apply(internalUltrasoundTest);

    }

    public Page<InternalUltrasoundTestDto> getAllByPatientId(Long patientId, List<UltrasoundOrderState> states, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return internalUltrasoundTestDao.getAllByPatientId(patientId,states,pageable).map(mapper);
    }

}
