package com.azu.hospital.Patients.MedicalHistory.services;

import com.azu.hospital.Patients.MedicalHistory.dao.MedicalHistoryDao;
import com.azu.hospital.Patients.MedicalHistory.dto.MedicalHistoryDto;
import com.azu.hospital.Patients.MedicalHistory.dto.MedicalHistoryDtoMapper;
import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.MedicalHistory.request.AddMedicalHistoryRequest;
import com.azu.hospital.Patients.MedicalHistory.request.UpdateMedicalHistoryRequest;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.wardBed.dao.BedDao;
import com.azu.hospital.bulding.ward.wardBed.entity.Bed;
import com.azu.hospital.exceptions.ApiError;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.patient.PatientTriageEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Service
public class MedicalHistoryService {

    private final MedicalHistoryDao medicalHistoryDao;
    private final MedicalHistoryDtoMapper mapper;
    private final PatientDao patientDao;
    private final WardDao wardDao;
    private final BedDao bedDao;

    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public MedicalHistoryService(
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


    public List<MedicalHistoryDto> getAllMedicalHistoryByPatientId(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


        return medicalHistoryDao.medicalHistoryByPatientId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()
                );

    }

    ;

    public MedicalHistoryDto getMedicalHistoryByPatientId(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (patient.getMedicalHistory().size() == 0) {
            throw new ResourceNotFoundException(
                    messages.getString("resourceNotFound")
            );
        }

        return mapper.toDto(
                medicalHistoryDao.
                        medicalHistoryByPatientId(
                                patient.getId()
                        ).get(0)
        );

    }

    ;




    @Transactional
    public void updateMedicalHistory(UpdateMedicalHistoryRequest request, Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        MedicalHistory medicalHistory = medicalHistoryDao.getMedicalHistoryById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        boolean isChanged = false;


        MedicalHistory newMedicalHistory = new MedicalHistory();

        newMedicalHistory.setPatient(medicalHistory.getPatient());
        if (request.getDx() != null
                && !Objects.equals(medicalHistory.getDx(), request.getDx())
        ) {
            newMedicalHistory.setDx(request.getDx());
            isChanged = true;
        } else {
            newMedicalHistory.setDx(medicalHistory.getDx());
        }

        if (request.getContagious() != null
                && !Objects.equals(medicalHistory.getContagious(), request.getContagious())) {
            newMedicalHistory.setContagious(request.getContagious());
            isChanged = true;
        } else {
            newMedicalHistory.setContagious(medicalHistory.getContagious());
        }
        if (request.getChiefComplaint() != null
                && !Objects.equals(medicalHistory.getChiefComplaint(), request.getChiefComplaint())) {
            newMedicalHistory.setChiefComplaint(request.getChiefComplaint());
            isChanged = true;
        } else {
            newMedicalHistory.setChiefComplaint(medicalHistory.getChiefComplaint());
        }
        if (request.getFamilyHistory() != null
                && !Objects.equals(medicalHistory.getFamilyHistory(), request.getFamilyHistory())) {
            newMedicalHistory.setFamilyHistory(request.getFamilyHistory());
            isChanged = true;
        } else {
            newMedicalHistory.setFamilyHistory(medicalHistory.getFamilyHistory());
        }
        if (request.getDrugHistoryAllergy() != null
                && !Objects.equals(medicalHistory.getDrugHistoryAllergy(), request.getDrugHistoryAllergy())) {
            newMedicalHistory.setDrugHistoryAllergy(request.getDrugHistoryAllergy());
            isChanged = true;
        } else {
            newMedicalHistory.setDrugHistoryAllergy(medicalHistory.getDrugHistoryAllergy());
        }
        if (request.getHistoryPresentIllness() != null
                && !Objects.equals(medicalHistory.getHistoryPresentIllness(), request.getHistoryPresentIllness())) {
            newMedicalHistory.setHistoryPresentIllness(request.getHistoryPresentIllness());
            isChanged = true;
        } else {
            newMedicalHistory.setHistoryPresentIllness(medicalHistory.getHistoryPresentIllness());
        }
        if (request.getTriage() != null
                && !Objects.equals(medicalHistory.getTriage(), request.getTriage())
        ) {
            newMedicalHistory.setTriage(request.getTriage());
            isChanged = true;
        } else {
            newMedicalHistory.setTriage(medicalHistory.getTriage());
        }

        if (request.getWardId() != null
                && !Objects.equals(medicalHistory.getPatient().getWard().getWardId(), request.getWardId())) {
            Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("wardNotFound")
                    )
            );
            newMedicalHistory.getPatient().setWard(ward);

            isChanged = true;
        } else {
            newMedicalHistory.getPatient().setWard(medicalHistory.getPatient().getWard());
        }

        if (request.getBedId() != null && !Objects.equals(medicalHistory.getPatient().getBed().getId(), request.getBedId())) {
            Patient patient= newMedicalHistory.getPatient();
            Bed oldBed = patient.getBed();
            oldBed.setPatient(null);
            patient.setBed(null);
            Bed bed = bedDao.getBedByIdAndWardIdAndBedNotReserved(request.getBedId()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            messages.getString("bedNotFound")
                    )
            );

            newMedicalHistory.getPatient().setBed(bed);
            bed.setPatient(newMedicalHistory.getPatient());
            bedDao.updateBed(bed);
            isChanged = true;
        } else {
            newMedicalHistory.getPatient().setBed(medicalHistory.getPatient().getBed());
        }

        if (!isChanged) {
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }


        patientDao.updatePatient(newMedicalHistory.getPatient());
        medicalHistoryDao.updateMedicalHistory(newMedicalHistory);

    }


    public void updateTriage(Long id, PatientTriageEnum triage) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        MedicalHistory medicalHistory = medicalHistoryDao.getLastByPatientId(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );


        if (medicalHistory.getTriage() == triage) {
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }

        medicalHistory.setTriage(triage);

        medicalHistoryDao.updateMedicalHistory(medicalHistory);
    }

}
