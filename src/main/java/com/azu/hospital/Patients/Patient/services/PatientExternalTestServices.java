package com.azu.hospital.Patients.Patient.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.utils.files.FileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PatientExternalTestServices {


    private final FileService fileService;

    private final PatientDao patientDao;


    public PatientExternalTestServices(
            FileService fileService,
            @Qualifier("patientRepo") PatientDao patientDao
    ) {
        this.fileService = fileService;
        this.patientDao = patientDao;
    }





}
