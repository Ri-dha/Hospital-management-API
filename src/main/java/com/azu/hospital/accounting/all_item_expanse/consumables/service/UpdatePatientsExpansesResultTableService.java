package com.azu.hospital.accounting.all_item_expanse.consumables.service;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.consumables.dao.PatientExpansesExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientsExpansesResultTableService implements PayExpanseFinalList {


    private final PatientExpansesExpanseResultTableDao dao;

    @Autowired
    public UpdatePatientsExpansesResultTableService(@Qualifier("PatientExpansesExpanseResultTableJpa") PatientExpansesExpanseResultTableDao dao) {
        this.dao = dao;
    }


    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientsExpansesResultTable> patientExpanseId = dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);
        for (PatientsExpansesResultTable patientsExpansesResultTable : patientExpanseId) {
            patientsExpansesResultTable.setIsArchived(true);
            patientsExpansesResultTable.setState(StockBillState.PAID);
            patientsExpansesResultTable.setDateOfPayment(LocalDate.now());
            dao.updatePatientExpansesExpanseResultTable (patientsExpansesResultTable);
        }
    }






}
