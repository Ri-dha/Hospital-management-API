package com.azu.hospital.consultant.consultantPatient.services;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.ConsultantEcgDto;
import com.azu.hospital.consultant.consultantPatient.dto.testDto.mappers.ConsultantEcgDtoMapper;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.consultant.consultantPatient.request.ConsultantPatientEcg;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.dto.EcgDto;
import com.azu.hospital.ecg.internal.dto.EcgDtoMapper;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class ConsultantEcgService {
    private final ConsultantPatientDao consultantPatientDao;
    private final NurseDao nurseDao;

    private final EcgDao ecgDao;

    private final ConsultantEcgDtoMapper mapper;

    private final FileService fileService;

    private final HttpServletRequest httpRequest;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ConsultantEcgService(
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("ecgJpa") EcgDao ecgDao,
            ConsultantEcgDtoMapper mapper,
            FileService fileService,
            HttpServletRequest httpRequest, ExceptionsMessageReturn messageReturn
    ) {
        this.consultantPatientDao = consultantPatientDao;
        this.nurseDao = nurseDao;
        this.ecgDao = ecgDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.httpRequest = httpRequest;
        this.messageReturn = messageReturn;
    }



    public ConsultantEcgDto createNewEcgTest(Long patientId, ConsultantPatientEcg request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        Ecg ecg = new Ecg(EcgStates.Waitting, TestDestination.Hospital);
        ecg.setConsultantPatient(consultantPatient);
        ecg.setNote(request.note());
        ecg = ecgDao.createNewEcgTest(ecg);

        return mapper.toDto(ecg);
    }



    public Page<ConsultantEcgDto> getEcgTest(Pageable pageable){
        Page<Ecg> ecgs = ecgDao.getAllConsultantTest(pageable);
        return ecgs.map(mapper::toDto);
    }

    public List<ConsultantEcgDto> getEcgByPatientIdTest(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(patientId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<Ecg> ecgs = ecgDao.getAllByConsultantPatientId(consultantPatient.getId());


        return ecgs.stream().map(mapper::toDto).collect(Collectors.toList());
    }


}
