package com.azu.hospital.ph.StockMangment.otherConsumablesList.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.dao.OtherConsumablesListDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.dto.OtherConsumablesListDto;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.dto.OtherConsumablesListDtoMapper;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.entity.OtherConsumablesList;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dao.OtherConsumablesDao;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDtoMapper;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class OtherConsumablesListGetService {

    private final OtherConsumablesListDao otherConsumablesListDao;

    private final OtherConsumablesListDtoMapper mapper;

    private final ExceptionsMessageReturn messageReturn;

    private final PatientDao patientDao;

    public OtherConsumablesListGetService(OtherConsumablesListDao otherConsumablesListDao, OtherConsumablesListDtoMapper mapper, ExceptionsMessageReturn messageReturn, PatientDao patientDao) {
        this.otherConsumablesListDao = otherConsumablesListDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
        this.patientDao = patientDao;
    }


    public OtherConsumablesListDto getOtherConsumablesList(Long listId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        return otherConsumablesListDao.getOtherConsumablesList(listId)
                .stream()
                .map(mapper)
                .findFirst()
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )

                );
    }


    public List<OtherConsumablesListDto> getAllOtherConsumablesListByPatientId(Long patientId){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        return otherConsumablesListDao.getAllOtherConsumablesListByPatientId(patientId)
                .stream()
                .map(mapper)
                .toList();
    }







}
