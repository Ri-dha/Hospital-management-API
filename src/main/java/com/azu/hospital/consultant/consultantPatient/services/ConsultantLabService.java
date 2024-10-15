package com.azu.hospital.consultant.consultantPatient.services;

import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantEcgDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantLabDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers.ConsultantLabDtoMapper;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.internal.dao.InternalLabDao;
import com.azu.hospital.lab_collection.internal.entity.InternalLabTest;
import com.azu.hospital.lab_collection.internal.request.CreateInternalLabRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.lab.InternalLabRequestStatusEnum;
import com.azu.hospital.utils.enums.TestDestination;
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
public class ConsultantLabService {

    private final InternalLabDao internalLabDao;

    private final ConsultantLabDtoMapper mapper;

    private final ConsultantPatientDao consultantPatientDao;
    private final ExceptionsMessageReturn messageReturn;

    public ConsultantLabService(
            @Qualifier("internalLabRepo") InternalLabDao internalLabDao,
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            ConsultantLabDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.internalLabDao = internalLabDao;
        this.consultantPatientDao = consultantPatientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    @Transactional
    public ConsultantLabDto createInternalLab(Long patientId , CreateInternalLabRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        // TODO: 10/14/2023 if nurse add state = InternalLabRequestStatusEnum.WaittingAcceptFromDoctor : InternalLabRequestStatusEnum.GettingSample

        // TODO: 10/14/2023 if nurse add nurse relation

        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId)
                .orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        InternalLabTest internalLabTest = new InternalLabTest(
                InternalLabRequestStatusEnum.GettingSample,
                request.getNote(),
                TestDestination.Consultant
        );

        internalLabTest.setConsultantPatient(consultantPatient);

        internalLabTest = internalLabDao.createNewInternalTest(internalLabTest);

        return mapper.toDto(internalLabTest);
    }



    public Page<ConsultantLabDto> getLabTest(Pageable pageable){
        Page<InternalLabTest> labTests = internalLabDao.getAllConsultantTest(pageable);
        return labTests.map(mapper::toDto);
    }

    public List<ConsultantLabDto> getLabTestByPatientIdTest(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<InternalLabTest> labTests = internalLabDao.getAllByConsultantPatientId(consultantPatient.getId());


        return labTests.stream().map(mapper::toDto).collect(Collectors.toList());
    }

}
