package com.azu.hospital.ultrasound.external.services;


import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.external.dao.ExternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.external.dto.ExternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.external.dto.ExternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.external.entity.ExternalUltrasoundTest;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class ExternalUltrasoundTestGetService {


    private final ExternalUltrasoundTestDao externalUltrasoundTestDao;
    private final ExceptionsMessageReturn messageReturn;

    private final PatientDao patientDao;
    private final ExternalUltrasoundTestDtoMapper mapper;

    public ExternalUltrasoundTestGetService(
            @Qualifier("externalUltrasoundTestRepo") ExternalUltrasoundTestDao externalUltrasoundTestDao, ExceptionsMessageReturn messageReturn,
            @Qualifier("patientRepo") PatientDao patientDao,
            ExternalUltrasoundTestDtoMapper mapper
    ) {
        this.externalUltrasoundTestDao = externalUltrasoundTestDao;
        this.messageReturn = messageReturn;
        this.patientDao = patientDao;
        this.mapper = mapper;
    }


    public Page<ExternalUltrasoundTestDto> getAllTests(
            List<UltrasoundTypeEnum> types,
            List<UltrasoundOrderState> states,
            String search,
            Pageable pageable) {
        return externalUltrasoundTestDao.getAllByFilter(types,states, search, pageable)
                .map(mapper);
    }


    public ExternalUltrasoundTestDto getTestById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        ExternalUltrasoundTest externalUltrasoundTest = externalUltrasoundTestDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return mapper.apply(externalUltrasoundTest);
    }


}
