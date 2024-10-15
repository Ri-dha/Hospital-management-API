package com.azu.hospital.Patients.MedicalHistory.services;

import com.azu.hospital.Patients.MedicalHistory.dao.MedicalHistoryDao;
import com.azu.hospital.Patients.MedicalHistory.dto.MedicalHistoryDto;
import com.azu.hospital.Patients.MedicalHistory.dto.MedicalHistoryDtoMapper;
import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.MedicalHistory.request.AddMedicalHistoryRequest;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
@Service
public class MedicalHistoryAddService {



    private final MedicalHistoryDao medicalHistoryDao;
    private final MedicalHistoryDtoMapper mapper;
    private final PatientDao patientDao;
    private final WardDao wardDao;
    private final BedDao bedDao;

    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public MedicalHistoryAddService(
            @Qualifier("MedicalHistoryRepo") MedicalHistoryDao medicalHistoryDao,
            MedicalHistoryDtoMapper mapper,
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("bedRepo") BedDao bedDao, ExceptionsMessageReturn messageReturn
    ) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.mapper = mapper;
        this.patientDao = patientDao;
        this.wardDao = wardDao;
        this.bedDao = bedDao;

        this.messageReturn = messageReturn;
    }




    public MedicalHistoryDto addMedicalHistory(AddMedicalHistoryRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(request.getPatientId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound")
                        )
                );


//        if (patient.getMedicalHistory().size() > 0) {
//            throw new ResourceNotFoundException(
//                    messages.getString("resourceNotFound")
//            );
//        }


        Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("wardNotFound") + " " + request.getWardId()
                )
        );

        Bed bed = bedDao.getBedByIdAndWardIdAndBedNotReserved(request.getBedId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bedNotFound") + " " + request.getBedId()
                )
        );

        try {
            MedicalHistory history = new MedicalHistory(
                    request.getDx(),
                    request.getContagious(),
                    request.getChiefComplaint(),
                    request.getFamilyHistory(),
                    request.getDrugHistoryAllergy(),
                    request.getHistoryPresentIllness(),
                    request.getTriage()
            );


            history.setPatient(patient);
            patient.setMedicalHistory(List.of(history));
            patient.setWard(ward);
            patient.setBed(bed);
            bed.setPatient(patient);
            MedicalHistory newHistory = medicalHistoryDao.insertMedicalHistory(history);
//        bedDao.updateBed(bed);
//        patientDao.updatePatient(patient);

            return mapper.toDto(newHistory);
        } catch (DuplicateResourceException e) {

            String errorMessage = "bedAssignForOtherPatients";

            throw new DuplicateResourceException(errorMessage);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = messages.getString("bedAssignForOtherPatients");
            throw new DuplicateResourceException(errorMessage);
        }

    }
}
