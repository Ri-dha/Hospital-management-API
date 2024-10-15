package com.azu.hospital.accounting.all_item_expanse.radiology.service;


import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.radiology.dao.PatientRadiologyExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientRadiologyExpanseResultTableServices implements PayExpanseFinalList {

    private final PatientRadiologyExpanseResultTableDao dao;


    public UpdatePatientRadiologyExpanseResultTableServices(PatientRadiologyExpanseResultTableDao dao) {
        this.dao = dao;
    }

    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientRadiologyExpanseResultTable> patientExpanseId = dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);

        for (PatientRadiologyExpanseResultTable patientExpanse : patientExpanseId) {
            patientExpanse.setIsArchived(true);
            patientExpanse.setState(StockBillState.PAID);
            patientExpanse.setDateOfPayment(LocalDate.now());
            dao.updatePatientRadiologyExpanseResultTable(patientExpanse);
        }
    }
}
