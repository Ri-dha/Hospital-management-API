package com.azu.hospital.consultant.consultantPatient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantLabDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantRadiologyDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantUltrasoundDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers.ConsultantRadiologyDtoMapper;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDto;
import com.azu.hospital.lab_collection.internal.dto.InternalLabDtoMapper;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.request.CreateInternalLabRequest;
import com.azu.hospital.radiology.internal.dao.InternalRadiologyTestDao;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDto;
import com.azu.hospital.radiology.internal.dto.InternalRadiologyTestDtoMapper;
import com.azu.hospital.radiology.internal.entity.InternalRadiologyTest;
import com.azu.hospital.radiology.internal.request.CreateInternalRadiologyTestRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.azu.hospital.utils.enums.radiology.RadiologyOrderState;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ConsultantRadiologyService {
    private final InternalRadiologyTestDao internalRadiologyTestDao;
    private final ExceptionsMessageReturn messageReturn;

    private final ConsultantPatientDao consultantPatientDao;
    private final ConsultantRadiologyDtoMapper mapper;

    public ConsultantRadiologyService(
            @Qualifier("internalRadiologyTestRepo") InternalRadiologyTestDao internalRadiologyTestDao, ExceptionsMessageReturn messageReturn,
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            ConsultantRadiologyDtoMapper mapper
    ) {
        this.internalRadiologyTestDao = internalRadiologyTestDao;
        this.messageReturn = messageReturn;
        this.consultantPatientDao = consultantPatientDao;
        this.mapper = mapper;
    }


    public ConsultantRadiologyDto createNewRadiologyTest(CreateInternalRadiologyTestRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        // TODO: 10/16/2023 Nurse Request
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(request.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


        InternalRadiologyTest internalRadiologyTest = new InternalRadiologyTest(
                request.getNote() ,
                request.getType(),
                RadiologyOrderState.Waitting,
                TestDestination.Hospital
        );

        internalRadiologyTest.setConsultantPatient(consultantPatient);

        internalRadiologyTest = internalRadiologyTestDao.createInternalRadiologyTest(internalRadiologyTest);


        return mapper.toDto(internalRadiologyTest);
    }


    public Page<ConsultantRadiologyDto> getRadiologyTest(Pageable pageable){
        Page<InternalRadiologyTest> internalRadiologyTests = internalRadiologyTestDao.getAllConsultantTest(pageable);
        return internalRadiologyTests.map(mapper::toDto);
    }

    public List<ConsultantRadiologyDto> getRadiologyTestByPatientId(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<InternalRadiologyTest> radiologyTests = internalRadiologyTestDao.getAllByConsultantPatientId(consultantPatient.getId());


        return radiologyTests.stream().map(mapper::toDto).collect(Collectors.toList());
    }



}
