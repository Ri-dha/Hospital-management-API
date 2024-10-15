package com.azu.hospital.accounting.all_item_expanse.other_consumbles.service;

import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;

import java.math.BigDecimal;

public interface IPatientOtherConsumablesResultTableService {

    void addPatientOtherConsumablesResultTable(Long patientId);
    void update(Long itemId,Long patientId,BigDecimal price, int count,String name);

}
