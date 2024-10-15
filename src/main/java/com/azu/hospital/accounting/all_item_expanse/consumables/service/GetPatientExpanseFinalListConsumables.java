package com.azu.hospital.accounting.all_item_expanse.consumables.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.GetPatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.consumables.dao.PatientExpansesExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.dto.PatientExpanseFinalListConsumablesDtoMapper;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class GetPatientExpanseFinalListConsumables implements GetPatientExpanseFinalList {

    private final PatientExpansesExpanseResultTableDao dao;
    private final PatientDao patientDao;
    private final PatientExpanseFinalListConsumablesDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public GetPatientExpanseFinalListConsumables(
            PatientExpansesExpanseResultTableDao dao,
            PatientDao patientDao,
            PatientExpanseFinalListConsumablesDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.dao = dao;
        this.patientDao = patientDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    @Override
    public ExpanseDtoScreen getPatientExpanseByPatientId(Long patientId) {

        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());


        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("patientNotFound") + " " + patientId
                        )
                );

        List<PatientsExpansesResultTable> expansesResultTable = dao.getAllPatientExpansesExpanseResultTableByPatientId(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();

        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Consumables");
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

        List<PatientsExpansesResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedTrue(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();

        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Consumables");
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

        List<PatientsExpansesResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patient.getId(), date);
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();

        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Consumables");
        screen.setExpanseDto(expanseDto);
        return screen;
    }
}
