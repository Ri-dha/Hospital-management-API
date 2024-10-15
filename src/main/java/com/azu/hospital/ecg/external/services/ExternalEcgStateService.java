package com.azu.hospital.ecg.external.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.ecg.external.dao.ExternalEcgDao;
import com.azu.hospital.ecg.external.dto.ExternalEcgDtoMapper;
import com.azu.hospital.ecg.external.entity.ExternalEcg;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExternalEcgStateService {

    private final NurseDao nurseDao;

    private final ExternalEcgDao externalEcgDao;



    private final HttpServletRequest httpRequest;

    @Autowired
    public ExternalEcgStateService(
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("externalEcgJpa") ExternalEcgDao externalEcgDao,
            HttpServletRequest httpRequest
    ) {
        this.nurseDao = nurseDao;
        this.externalEcgDao = externalEcgDao;
        this.httpRequest = httpRequest;
    }


    public void acceptFromNurse(Long testId){

        Nurse nurse = nurseDao.getNurseByToken(httpRequest.getHeader("Authorization").replace("Bearer " ,"")).orElseThrow(
                () -> new ResourceNotFoundException("Nurse Doesn't Exists")
        );


        ExternalEcg ecg = externalEcgDao.findTestById(testId).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        if (ecg.getState() != EcgStates.Waitting){
            throw new BadRequestException("Can't Accept This Ecg Test");
        }

        ecg.setNurse(nurse);
        ecg.setState(EcgStates.Accepted);

        externalEcgDao.updateEcgTest(ecg);
    }



}
