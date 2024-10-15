package com.azu.hospital.ecg.internal.services;

import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.ecg.internal.dao.EcgDao;
import com.azu.hospital.ecg.internal.dto.EcgDto;
import com.azu.hospital.ecg.internal.dto.EcgDtoMapper;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class EcgGetService {

    private final PatientDao patientDao;
    private final NurseDao nurseDao;

    private final EcgDao ecgDao;

    private final EcgDtoMapper mapper;

    private final HttpServletRequest httpRequest;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public EcgGetService(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            @Qualifier("ecgJpa") EcgDao ecgDao,
            @Qualifier("ecgDtoMapper") EcgDtoMapper mapper,
            HttpServletRequest httpRequest, ExceptionsMessageReturn messageReturn
    ) {
        this.patientDao = patientDao;
        this.nurseDao = nurseDao;
        this.ecgDao = ecgDao;
        this.mapper = mapper;
        this.httpRequest = httpRequest;
        this.messageReturn = messageReturn;
    }


    public Page<EcgDto> getAllByFilter(
            String search,
            List<EcgStates> state,
            Pageable pageable) {


        return ecgDao.getWithFilter(search, state, pageable).map(mapper);
    }

    public EcgDto getById(Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Ecg ecg = ecgDao.findTestById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("ecgTestNotFound") + " " + id
        ));
        return mapper.apply(ecg);
    }

    public Page<EcgDto> getAllByPatientId(Long id, Pageable pageable) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        patientDao.getPatientById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("patientNotFound") + " " + id));
        return ecgDao.getAllByPatientId(id, pageable).map(mapper);

    }


}
