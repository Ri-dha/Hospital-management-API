package com.azu.hospital.consultant.consultantPatient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantRadiologyDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantUltrasoundDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers.ConsultantRadiologyDtoMapper;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers.ConsultantUltrasoundDtoMapper;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.ultrasound.internal.dao.InternalUltrasoundTestDao;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDto;
import com.azu.hospital.ultrasound.internal.dto.InternalUltrasoundTestDtoMapper;
import com.azu.hospital.ultrasound.internal.entity.InternalUltrasoundTest;
import com.azu.hospital.ultrasound.internal.request.CreateInternalUltrasoundTestRequest;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundOrderState;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ConsultantUltrasoundService {

    private final InternalUltrasoundTestDao internalUltrasoundTestDao;

    private final ConsultantPatientDao consultantPatientDao;
    private final ConsultantUltrasoundDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public ConsultantUltrasoundService(
            @Qualifier("internalUltrasoundTestRepo") InternalUltrasoundTestDao internalUltrasoundTestDao,
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            ConsultantUltrasoundDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.internalUltrasoundTestDao = internalUltrasoundTestDao;
        this.consultantPatientDao = consultantPatientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public ConsultantUltrasoundDto createNewUltrasoundTest(CreateInternalUltrasoundTestRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        // TODO: 10/16/2023 Nurse Request
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(
                request.getPatientId()
        ).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


        InternalUltrasoundTest internalUltrasoundTest = new InternalUltrasoundTest(
                request.getNote() ,
                request.getType(),
                UltrasoundOrderState.Waitting ,
                TestDestination.Consultant
        );

        internalUltrasoundTest.setConsultantPatient(consultantPatient);

        internalUltrasoundTest = internalUltrasoundTestDao.createInternalUltrasoundTest(internalUltrasoundTest);


        return mapper.toDto(internalUltrasoundTest);
    }


    public Page<ConsultantUltrasoundDto> getUltrasoundTest(Pageable pageable){
        Page<InternalUltrasoundTest> internalUltrasoundTests = internalUltrasoundTestDao.getAllConsultantTest(pageable);
        return internalUltrasoundTests.map(mapper::toDto);
    }

    public List<ConsultantUltrasoundDto> getUltrasoundTestByPatientId(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<InternalUltrasoundTest> ultrasoundTests =
                internalUltrasoundTestDao.getAllByConsultantPatientId(consultantPatient.getId());


        return ultrasoundTests.stream().map(mapper::toDto).collect(Collectors.toList());
    }


}
