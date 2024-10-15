package com.azu.hospital.ecg.external.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.ecg.external.dao.ExternalEcgDao;
import com.azu.hospital.ecg.external.dto.ExternalEcgDto;
import com.azu.hospital.ecg.external.dto.ExternalEcgDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalEcgGetService {

    private final NurseDao nurseDao;

    private final ExternalEcgDao externalEcgDao;

    private final ExternalEcgDtoMapper mapper;


    private final HttpServletRequest httpRequest;

    @Autowired
    public ExternalEcgGetService(
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("externalEcgJpa") ExternalEcgDao externalEcgDao,
            ExternalEcgDtoMapper mapper,
            HttpServletRequest httpRequest
    ) {
        this.nurseDao = nurseDao;
        this.externalEcgDao = externalEcgDao;
        this.mapper = mapper;
        this.httpRequest = httpRequest;
    }


    public List<ExternalEcgDto> getAllByNurseEcgTest(){
        Nurse nurse = nurseDao.getNurseByToken(httpRequest.getHeader("Authorization").replace("Bearer " ,"")).orElseThrow(
                () -> new ResourceNotFoundException("Nurse Doesn't Exists")
        );


        return externalEcgDao.getAllByNurseId(
                        nurse.getId()).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }


    public List<ExternalEcgDto> getAllByPatientEcgTest(){

        return externalEcgDao.getAllByPatientId().
                stream().
                map(mapper::toDto).
                collect(Collectors.toList());
    }


}
