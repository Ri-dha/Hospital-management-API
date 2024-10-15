package com.azu.hospital.patient_expances.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.patient_expances.dao.PatientExpanseDao;
import com.azu.hospital.patient_expances.dao.PatientExpanseListDao;
import com.azu.hospital.patient_expances.dto.PatientExpanseDto;
import com.azu.hospital.patient_expances.dto.PatientExpanseDtoMapper;
import com.azu.hospital.patient_expances.dto.PatientExpanseListDto;
import com.azu.hospital.patient_expances.dto.PatientExpanseListDtoMapper;

import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class PatientExpanseGetServices {

    private final PatientExpanseListDao dao;
    private final PatientExpanseListDtoMapper expanseListDtoMapper;
    private final PatientExpanseDao patientExpanseDao;
    private final PatientExpanseDtoMapper patientExpanseDtoMapper;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public PatientExpanseGetServices(PatientExpanseListDao dao, PatientExpanseListDtoMapper expanseListDtoMapper, PatientExpanseDao patientExpanseDao, PatientExpanseDtoMapper patientExpanseDtoMapper, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.expanseListDtoMapper = expanseListDtoMapper;
        this.patientExpanseDao = patientExpanseDao;
        this.patientExpanseDtoMapper = patientExpanseDtoMapper;
        this.messageReturn = messageReturn;
    }


    public PatientExpanseListDto getPatientExpanseListById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return dao.getPatientExpanseById(id)
                .map(expanseListDtoMapper)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
    }

    public PatientExpanseDto getPatientExpanseById(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return patientExpanseDao.getPatientExpanseById(id)
                .map(patientExpanseDtoMapper).orElseThrow(
                        ()-> new ResourceNotFoundException
                                (messages.getString("resourceNotFound"))
                );
    }



    public List<PatientExpanseListDto> getAllPatientExpanseListByPatientId(Long patientId){
        return dao.getAllPatientExpanseListByPatientId(patientId)
                .stream()
                .map(expanseListDtoMapper)
                .toList();
    }

    public Page<PatientExpanseListDto> getAllPatientExpanseList(String patientName , Pageable pageable){
        Page<PatientExpanseList> expenseListPage = dao.getAllPatientExpanseList(patientName,pageable);

        List<PatientExpanseListDto> expenseListDtoList = expenseListPage.getContent().stream()
                .map(expanseListDtoMapper)
                .toList();

        return new PageImpl<>(expenseListDtoList, pageable, expenseListPage.getTotalElements());
    }

}
