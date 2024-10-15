package com.azu.hospital.accounting.all_item_expanse.opreation.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientOperationExpanseResultTableDao {

    void createPatientOperationExpanseResultTable(PatientOperationExpanseResultTable request);

    void updatePatientOperationExpanseResultTable(PatientOperationExpanseResultTable update);
    void updatePatientOperationExpanseResult(List<PatientOperationExpanseResultTable> update);

    Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableById(Long id);

    List<PatientOperationExpanseResultTable> getAllPatientOperationExpanseResultTableByPatientId(Long patientId  , StockBillState state);

    Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableByPatientId(Long patientId);

    List<PatientOperationExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientOperationExpanseResultTable> getPatientOperationExpanseResultTableByOperationIdAndPatientId(Long operationId, Long patientId);

    List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
