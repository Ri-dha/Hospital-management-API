package com.azu.hospital.ecg.external.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.ecg.external.dao.ExternalEcgDao;
import com.azu.hospital.ecg.external.dto.ExternalEcgDto;
import com.azu.hospital.ecg.external.dto.ExternalEcgDtoMapper;
import com.azu.hospital.ecg.external.entity.EcgPatientData;
import com.azu.hospital.ecg.external.entity.ExternalEcg;
import com.azu.hospital.ecg.external.request.ExternalAddResultToEcgTest;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import com.azu.hospital.utils.files.File;
import com.azu.hospital.utils.files.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalEcgService {

    private final PatientDao patientDao;
    private final NurseDao nurseDao;

    private final ExternalEcgDao externalEcgDao;

    private final ExternalEcgDtoMapper mapper;

    private final FileService fileService;

    private final HttpServletRequest httpRequest;

    @Autowired
    public ExternalEcgService(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("externalEcgJpa") ExternalEcgDao externalEcgDao,
            ExternalEcgDtoMapper mapper,
            FileService fileService,
            HttpServletRequest httpRequest
    ) {
        this.patientDao = patientDao;
        this.nurseDao = nurseDao;
        this.externalEcgDao = externalEcgDao;
        this.mapper = mapper;
        this.fileService = fileService;
        this.httpRequest = httpRequest;
    }



    public ExternalEcgDto createNewEcgTest(EcgPatientData ecgPatientData){

        ExternalEcg ecg = new ExternalEcg(EcgStates.Waitting , ecgPatientData);
        ecg = externalEcgDao.createNewEcgTest(ecg);

        return mapper.toDto(ecg);
    }

    public ExternalEcgDto addResult(ExternalAddResultToEcgTest request) throws IOException {

        ExternalEcg ecg = externalEcgDao.findTestById(request.getEcgId()).orElseThrow(
                () -> new ResourceNotFoundException("Test Doesn't Exists")
        );

        if (ecg.getState() != EcgStates.Waitting){
            throw new BadRequestException("Can't Add Result To This Test");
        }

        if (request.getFiles() != null && !request.getFiles().isEmpty()){
            List<File> fileList = new ArrayList<>();
            for(MultipartFile files: request.getFiles()){
                if (!files.isEmpty()){
                    String url = fileService.saveFile(files);
                    File file = new File(files.getContentType() , "Ecg" ,url );
                    fileList.add(file);
                }
            }
            ecg.setFiles(fileList);
        }

        ecg.setState(EcgStates.Completed);

        return mapper.toDto(externalEcgDao.updateWitDataEcgTest(ecg));

    }




}
