package com.azu.hospital.accounting.all_item_expanse.consumables.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientExpansesExpanseResultTableDao {

    void createPatientExpansesExpanseResultTable(PatientsExpansesResultTable request);

    void updatePatientExpansesExpanseResultTable(PatientsExpansesResultTable update);

    Optional<PatientsExpansesResultTable> getPatientExpansesExpanseResultTableById(Long id);

    List<PatientsExpansesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId , StockBillState state);

    List<PatientsExpansesResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientsExpansesResultTable>getPatientExpansesExpanseResultTableByDrugId(Long patientId);


    List<BillsDtoSum<String>> sumExpansesQuantityPatientId(Long patientId);

    Optional<PatientsExpansesResultTable> getPatientExpansesResultTableByItemIdAndPriceAndPatientId(Long consumableId, BigDecimal price, Long patientI);

    List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);

}
