package com.azu.hospital.accounting.all_item_expanse.other_consumbles.service;


import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao.PatientOtherConsumablesResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.request.PatientOtherConsumablesResultTableUpdateRequest;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientOtherConsumablesResultTableServices implements PayExpanseFinalList {

    private final PatientOtherConsumablesResultTableDao dao;

    @Autowired
    public UpdatePatientOtherConsumablesResultTableServices(PatientOtherConsumablesResultTableDao dao) {
        this.dao = dao;
    }


    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientOtherConsumablesResultTable> expanseResultTables = dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);

        for (PatientOtherConsumablesResultTable expanseResultTable : expanseResultTables) {
            expanseResultTable.setIsArchived(true);
            expanseResultTable.setState(StockBillState.PAID);
            expanseResultTable.setDateOfPayment(LocalDate.now());
            dao.updatePatientOtherConsumablesResultTable(expanseResultTable);
        }
    }


    public void updatePricing(List<PatientOtherConsumablesResultTableUpdateRequest> requests){

        for (PatientOtherConsumablesResultTableUpdateRequest request : requests) {
            PatientOtherConsumablesResultTable expanseResultTable = dao.getPatientOtherConsumablesResultTableById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Expanse not found"));
            expanseResultTable.setItemPrice(request.getPrice());
            expanseResultTable.setTotalPrice(request.getPrice().multiply(BigDecimal.valueOf(expanseResultTable.getItemCount())));

            dao.updatePatientOtherConsumablesResultTable(expanseResultTable);
        }

    }


}
