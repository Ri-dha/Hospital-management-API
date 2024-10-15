package com.azu.hospital.consultant.consultantPatient.vitalSign.services;

import com.azu.hospital.consultant.consultantPatient.dao.ConsultantPatientDao;
import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import com.azu.hospital.consultant.consultantPatient.vitalSign.dao.ConsultantVitalSignDao;
import com.azu.hospital.consultant.consultantPatient.vitalSign.dto.ConsultantVitalSignDto;
import com.azu.hospital.consultant.consultantPatient.vitalSign.dto.ConsultantVitalSignDtoMapper;
import com.azu.hospital.consultant.consultantPatient.vitalSign.entity.ConsultantVitalSign;
import com.azu.hospital.consultant.consultantPatient.vitalSign.request.ConsultantCreateVitalSignRequest;
import com.azu.hospital.consultant.consultantPatient.vitalSign.request.ConsultantUpdateVitalSignRequest;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
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
public class ConsultantVitalSignService {

    private final ConsultantVitalSignDao vitalSignDao;
    private final ConsultantPatientDao consultantPatientDao;

    private final ConsultantVitalSignDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public ConsultantVitalSignService(
            @Qualifier("consultantVitalSignJpa") ConsultantVitalSignDao vitalSignDao,
            @Qualifier("consultantPatientJpa") ConsultantPatientDao consultantPatientDao,
            ConsultantVitalSignDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.vitalSignDao = vitalSignDao;
        this.consultantPatientDao = consultantPatientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public ConsultantVitalSignDto createNewVitalSign(ConsultantCreateVitalSignRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantPatient consultantPatient = consultantPatientDao.findByPatientId(
                request.getPatientId()
        ).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        ConsultantVitalSign vitalSign = new ConsultantVitalSign(
                request.getPulseRate(),
                request.getSysBloodPressure(),
                request.getDiaBloodPressure(),
                request.getBloodSugar(),
                request.getBodyTemp(),
                request.getSkinTemp(),
                request.getSpo(),
                request.getRespiratoryRate(),
                request.getPainLevel()
        );

        vitalSign.setConsultantPatient(consultantPatient);

        vitalSign = vitalSignDao.createNewVitalSign(vitalSign);

        return mapper.toDto(vitalSign);
    }


    public void updateVitalSign(ConsultantUpdateVitalSignRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantVitalSign vitalSign = vitalSignDao.fundById(request.getVitalSignId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        ConsultantVitalSign newVitalSign = new ConsultantVitalSign(mapper.toEntity(request));


        if (request.getSysBloodPressure() == null) {
            newVitalSign.setSysBloodPressure(vitalSign.getSysBloodPressure());
        }

        if (request.getDiaBloodPressure() == null) {
            newVitalSign.setDiaBloodPressure(vitalSign.getDiaBloodPressure());
        }

        if (request.getBloodSugar() == null) {
            newVitalSign.setBloodSugar(vitalSign.getBloodSugar());
        }

        if (request.getBodyTemp() == null) {
            newVitalSign.setBodyTemp(vitalSign.getBodyTemp());
        }

        if (request.getSkinTemp() == null) {
            newVitalSign.setSkinTemp(vitalSign.getSkinTemp());
        }

        if (request.getSpo() == null) {
            newVitalSign.setSpo(vitalSign.getSpo());
        }

        if (request.getRespiratoryRate() == null) {
            newVitalSign.setRespiratoryRate(vitalSign.getRespiratoryRate());
        }

        if (request.getPainLevel() == null) {
            newVitalSign.setPainLevel(vitalSign.getPainLevel());
        }

        if (
                newVitalSign.getSysBloodPressure().equals(vitalSign.getSysBloodPressure()) &&
                        newVitalSign.getDiaBloodPressure().equals(vitalSign.getDiaBloodPressure()) &&
                        newVitalSign.getBloodSugar().equals(vitalSign.getBloodSugar()) &&
                        newVitalSign.getBodyTemp().equals(vitalSign.getBodyTemp()) &&
                        newVitalSign.getSkinTemp().equals(vitalSign.getSkinTemp()) &&
                        newVitalSign.getSpo().equals(vitalSign.getSpo()) &&
                        newVitalSign.getRespiratoryRate().equals(vitalSign.getRespiratoryRate()) &&
                        newVitalSign.getPainLevel().equals(vitalSign.getPainLevel())
        ) {
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }


        vitalSign.setConsultantPatient(vitalSign.getConsultantPatient());
    }


    public ConsultantVitalSignDto findById(Long vitalSignId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        ConsultantVitalSign vitalSign = vitalSignDao.fundById(vitalSignId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound"
                ))
        );

        return mapper.toDto(vitalSign);
    }


    public List<ConsultantVitalSignDto> getAllVitalSignByPatientId(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        consultantPatientDao.findByPatientId(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );
        return vitalSignDao.getAllByPatientId(patientId).
                stream().
                map(mapper::toDto).
                collect(Collectors.toList()
                );

    }


}
