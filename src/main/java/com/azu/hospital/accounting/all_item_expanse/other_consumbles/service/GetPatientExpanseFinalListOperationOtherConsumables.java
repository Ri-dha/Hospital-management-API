package com.azu.hospital.accounting.all_item_expanse.other_consumbles.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.GetPatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao.PatientOtherConsumablesResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dto.PatientExpanseFinalListOtherConsumableDtoMapper;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class GetPatientExpanseFinalListOperationOtherConsumables implements GetPatientExpanseFinalList {

    private final PatientDao patientDao;
    private final PatientOtherConsumablesResultTableDao dao;
    private final PatientExpanseFinalListOtherConsumableDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;
    @Autowired
    public GetPatientExpanseFinalListOperationOtherConsumables(
            @Qualifier("patientRepo") PatientDao patientDao,
            @Qualifier("PatientOtherConsumablesResultTableJpa") PatientOtherConsumablesResultTableDao dao,
            @Qualifier("patientExpanseFinalListOtherConsumableDtoMapper") PatientExpanseFinalListOtherConsumableDtoMapper mapper,
            @Qualifier("exceptionsMessageReturn") ExceptionsMessageReturn messageReturn) {
        this.patientDao = patientDao;
        this.dao = dao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    @Override
    public ExpanseDtoScreen getPatientExpanseByPatientId(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        List<PatientOtherConsumablesResultTable> expansesResultTable = dao.getAllPatientExpansesExpanseResultTableByPatientId(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Other Consumables");
        screen.setExpanseDto(expanseDto);
        return screen;
    }

    @Override
    public ExpanseDtoScreen getArchivedPatientExpanseByPatientId(Long patientId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        List<PatientOtherConsumablesResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedTrue(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Other Consumables");
        screen.setExpanseDto(expanseDto);
        return screen;




    }

    @Override
    public ExpanseDtoScreen GetPatientExpanseFinalListByIdAndDate(Long patientId, LocalDate date) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        List<PatientOtherConsumablesResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patient.getId(), date);
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Other Consumables");
        screen.setExpanseDto(expanseDto);
        return screen;


    }
}
