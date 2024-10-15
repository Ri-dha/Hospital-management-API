package com.azu.hospital.accounting.all_item_expanse.Labs.service;

import com.azu.hospital.accounting.all_item_expanse.Labs.dao.PatientLabExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientLabExpanseResultTableService implements PayExpanseFinalList {
    private final PatientLabExpanseResultTableDao dao;

    @Autowired
    public UpdatePatientLabExpanseResultTableService(PatientLabExpanseResultTableDao dao) {
        this.dao = dao;
    }


    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientLabExpanseResultTable> labExpanseResultTables= dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);
        for (PatientLabExpanseResultTable labExpanseResultTable : labExpanseResultTables) {
            labExpanseResultTable.setIsArchived(true);
            labExpanseResultTable.setState(StockBillState.PAID);
            labExpanseResultTable.setDateOfPayment(LocalDate.now());
            dao.updatePatientLabExpanseResultTable(labExpanseResultTable);
        }

    }
}
