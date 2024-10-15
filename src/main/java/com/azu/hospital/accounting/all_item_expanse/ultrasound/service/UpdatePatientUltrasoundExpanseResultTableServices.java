package com.azu.hospital.accounting.all_item_expanse.ultrasound.service;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.dao.PatientUltrasoundExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientUltrasoundExpanseResultTableServices implements PayExpanseFinalList {

    private final PatientUltrasoundExpanseResultTableDao dao;

    @Autowired
    public UpdatePatientUltrasoundExpanseResultTableServices(PatientUltrasoundExpanseResultTableDao dao) {
        this.dao = dao;
    }


    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientUltrasoundExpanseResultTable> patientExpanseId= dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);

        for (PatientUltrasoundExpanseResultTable patientExpanse : patientExpanseId) {
            patientExpanse.setIsArchived(true);
            patientExpanse.setState(StockBillState.PAID);
            patientExpanse.setDateOfPayment(LocalDate.now());
            dao.updatePatientUltrasoundExpanseResultTable(patientExpanse);
        }
    }
}
