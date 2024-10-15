package com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao;

import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientOtherConsumablesResultTableDao {

    void createPatientOtherConsumablesResultTable(PatientOtherConsumablesResultTable request);

    void updatePatientOtherConsumablesResultTable(PatientOtherConsumablesResultTable update);
    void updatePatientOtherConsumablesResult(List<PatientOtherConsumablesResultTable> update);

    Optional<PatientOtherConsumablesResultTable> getPatientOtherConsumablesResultTableById(Long id);

    List<PatientOtherConsumablesResultTable> getAllPatientOtherConsumablesResultTableByPatientId(Long patientId , StockBillState state);

    Optional<PatientOtherConsumablesResultTable>getPatientOtherConsumablesResultTableByDrugId(Long itemId);

    List<PatientOtherConsumablesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientOtherConsumablesResultTable> getPatientOtherConsumablesResultTableByDrugIdAndPatientId(Long itemId, Long patientId);

    //update price
    Optional<PatientOtherConsumablesResultTable> findByNameAndPatientId(Long itemId, Long patientId);

    List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);


    List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
