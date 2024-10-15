package com.azu.hospital.accounting.all_item_expanse.drugs.services;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.drugs.dao.PatientDrugsExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdatePatientDrugsExpanseResultTableServices implements PayExpanseFinalList {

    private final PatientDrugsExpanseResultTableDao dao;

    @Autowired
    public UpdatePatientDrugsExpanseResultTableServices(PatientDrugsExpanseResultTableDao dao) {
        this.dao = dao;
    }

    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientDrugsExpanseResultTable> patientExpanseId = dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);
        for (PatientDrugsExpanseResultTable patientExpanse : patientExpanseId) {
            patientExpanse.setIsArchived(true);
            patientExpanse.setState(StockBillState.PAID);
            dao.updatePatientDrugsExpanseResultTable(patientExpanse);
        }
    }
}
