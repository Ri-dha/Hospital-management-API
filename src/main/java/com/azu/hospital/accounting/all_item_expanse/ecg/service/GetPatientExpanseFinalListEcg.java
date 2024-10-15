package com.azu.hospital.accounting.all_item_expanse.ecg.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDto;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.dto.ExpanseDtoScreen;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.GetPatientExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.ecg.dao.PatientEcgExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ecg.dto.PatientExpanseFinalListEcgDtoMapper;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class GetPatientExpanseFinalListEcg implements GetPatientExpanseFinalList {

    private final PatientDao patientDao;
    private final PatientEcgExpanseResultTableDao dao;
    private final PatientExpanseFinalListEcgDtoMapper mapper;

    @Autowired
    public GetPatientExpanseFinalListEcg(PatientDao patientDao, PatientEcgExpanseResultTableDao dao, PatientExpanseFinalListEcgDtoMapper mapper) {
        this.patientDao = patientDao;
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public ExpanseDtoScreen getPatientExpanseByPatientId(Long patientId) {
        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Patient not found"
                        )
                );

        List<PatientEcgExpanseResultTable> expansesResultTable = dao.getAllPatientExpansesExpanseResultTableByPatientId(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Ecg");
        screen.setExpanseDto(expanseDto);
        return screen;
    }

    @Override
    public ExpanseDtoScreen getArchivedPatientExpanseByPatientId(Long patientId) {

        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Patient not found"
                        )
                );

        List<PatientEcgExpanseResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedTrue(patient.getId());
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Ecg");
        screen.setExpanseDto(expanseDto);
        return screen;

    }

    @Override
    public ExpanseDtoScreen GetPatientExpanseFinalListByIdAndDate(Long patientId, LocalDate date) {


        Patient patient = patientDao.getPatientById(patientId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Patient not found"
                        )
                );

        List<PatientEcgExpanseResultTable> expansesResultTable = dao.findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(patient.getId(), date);
        List<ExpanseDto> expanseDto = expansesResultTable
                .stream()
                .map(mapper::getExpanseDtoList)
                .toList();
        ExpanseDtoScreen screen = new ExpanseDtoScreen();
        screen.setName("Ecg");
        screen.setExpanseDto(expanseDto);
        return screen;

    }

}
