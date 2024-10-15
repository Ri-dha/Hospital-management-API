package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.service;


import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dao.ConsultantPatientMedicalHistoryDao;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto.ConsultantPatientMedicalHistoryDto;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dto.ConsultantPatientMedicalHistoryDtoMapper;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity.ConsultantPatientMedicalHistory;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request.CreateConsultantPatientMedicalHistoryRequest;
import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.request.UpdateConsultantPatientMedicalHistoryRequest;
import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Service
public class ConsultantPatientMedicalHistoryService {

    private final ConsultantPatientMedicalHistoryDao medicalHistoryDao;
    private final ConsultantPatientDao consultantPatientDao;

    private final ConsultantPatientMedicalHistoryDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public ConsultantPatientMedicalHistoryService(
            @Qualifier("consultantPatientMedicalHistoryJpa") ConsultantPatientMedicalHistoryDao medicalHistoryDao,
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            ConsultantPatientMedicalHistoryDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.consultantPatientDao = consultantPatientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }



    public ConsultantPatientMedicalHistoryDto getLastMedicalHistory(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
          consultantPatientDao.findByPatientId(patientId).orElseThrow(
                  () -> new ResourceNotFoundException("Patient Doesn't Exists")
          );

          return mapper.toDto(medicalHistoryDao.getLastMedicalHistory(patientId));
    }


    public List<ConsultantPatientMedicalHistoryDto> getHistoryOfMedicalHistory(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        consultantPatientDao.findByPatientId(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        return medicalHistoryDao.getAllMedicalHistory(patientId).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList()
                );
    }
    public ConsultantPatientMedicalHistoryDto createNewMedicalHistory(CreateConsultantPatientMedicalHistoryRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient =
                consultantPatientDao.findByPatientId(request.getPatientId()).orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );

        ConsultantPatientMedicalHistory medicalHistory = new ConsultantPatientMedicalHistory(
                request.getDx(),
                request.getHistoryOfPresentIllness(),
                request.getPastMedicalHistory(),
                request.getPastSurgicalHistory(),
                request.getAllergy(),
                request.getContagiousDisease()
        );

        medicalHistory.setConsultantPatient(consultantPatient);

        medicalHistory = medicalHistoryDao.newConsultantPatientMedicalHistory(medicalHistory);

        return mapper.toDto(medicalHistory);
    }

    public void updatePatientMedicalHistory(UpdateConsultantPatientMedicalHistoryRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatientMedicalHistory medicalHistory = medicalHistoryDao.findById(
                request.getMedicalHistoryId()
        ).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        ConsultantPatientMedicalHistory newMedicalHistory = new ConsultantPatientMedicalHistory(
                mapper.ToEntity(request)
        );


        if (request.getDx() == null) {
            newMedicalHistory.setDx(medicalHistory.getDx());
        }

        if (request.getPastMedicalHistory() == null) {
            newMedicalHistory.setPastMedicalHistory(medicalHistory.getPastMedicalHistory());
        }

        if (request.getPastSurgicalHistory() == null) {
            newMedicalHistory.setPastSurgicalHistory(medicalHistory.getPastSurgicalHistory());
        }

        if (request.getHistoryOfPresentIllness() == null) {
            newMedicalHistory.setHistoryOfPresentIllness(medicalHistory.getHistoryOfPresentIllness());
        }

        if (request.getAllergy() == null) {
            newMedicalHistory.setAllergy(medicalHistory.getAllergy());
        }

        if (request.getContagiousDisease() == null) {
            newMedicalHistory.setContagiousDisease(medicalHistory.getContagiousDisease());
        }


        if (
                Objects.equals(medicalHistory.getDx(), request.getDx()) &&
                        Objects.equals(medicalHistory.getPastSurgicalHistory(), request.getPastMedicalHistory()) &&
                        Objects.equals(medicalHistory.getHistoryOfPresentIllness(), request.getHistoryOfPresentIllness()) &&
                        Objects.equals(medicalHistory.getPastMedicalHistory(), request.getPastMedicalHistory()) &&
                        Objects.equals(medicalHistory.getAllergy(), request.getAllergy()) &&
                        Objects.equals(medicalHistory.getContagiousDisease(), request.getContagiousDisease())
        ) {
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }

        newMedicalHistory.setConsultantPatient(medicalHistory.getConsultantPatient());

        medicalHistoryDao.newConsultantPatientMedicalHistory(newMedicalHistory);

    }
}
