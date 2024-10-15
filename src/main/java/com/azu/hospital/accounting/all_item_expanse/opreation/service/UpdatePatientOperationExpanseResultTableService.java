package com.azu.hospital.accounting.all_item_expanse.opreation.service;

import com.azu.hospital.accounting.all_item_expanse.base_expanse.services.interfaces.PayExpanseFinalList;
import com.azu.hospital.accounting.all_item_expanse.opreation.PatientOperationExpanseResultTableUpdateRequest;
import com.azu.hospital.accounting.all_item_expanse.opreation.dao.PatientOperationExpanseResultTableDao;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.request.PatientOtherConsumablesResultTableUpdateRequest;
import com.azu.hospital.utils.enums.StockBillState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
public class UpdatePatientOperationExpanseResultTableService implements PayExpanseFinalList {

    private final PatientOperationExpanseResultTableDao dao;

    @Autowired
    public UpdatePatientOperationExpanseResultTableService(PatientOperationExpanseResultTableDao dao) {
        this.dao = dao;
    }


    @Override
    public void payExpanseFinalList(Long patientId) {
        List<PatientOperationExpanseResultTable> expanseResultTables = dao.getAllPatientExpansesExpanseResultTableByPatientId(patientId);
        for (PatientOperationExpanseResultTable expanseResultTable : expanseResultTables) {
            expanseResultTable.setIsArchived(true);
            expanseResultTable.setState(StockBillState.PAID);
            expanseResultTable.setDateOfPayment(LocalDate.now());
            dao.updatePatientOperationExpanseResultTable(expanseResultTable);
        }
    }

    public void updatePricing(List<PatientOperationExpanseResultTableUpdateRequest> requests){
        for (PatientOperationExpanseResultTableUpdateRequest request : requests) {
            PatientOperationExpanseResultTable expanseResultTable = dao.getPatientOperationExpanseResultTableById(request.getId()).orElseThrow(
                    () -> new RuntimeException("Expanse not found")
            );
            expanseResultTable.setOperationPrice(request.getPrice());
            expanseResultTable.setTotalOperationPrice(request.getPrice().multiply(BigDecimal.valueOf(expanseResultTable.getOperationCount())));
            dao.updatePatientOperationExpanseResultTable(expanseResultTable);
        }
    }
}
