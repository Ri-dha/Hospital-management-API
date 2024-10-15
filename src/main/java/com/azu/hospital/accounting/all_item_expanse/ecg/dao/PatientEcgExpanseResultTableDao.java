package com.azu.hospital.accounting.all_item_expanse.ecg.dao;

import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientEcgExpanseResultTableDao {

    void createPatientEcgExpanseResultTable(PatientEcgExpanseResultTable request);

    void updatePatientEcgExpanseResultTable(PatientEcgExpanseResultTable update);

    Optional<PatientEcgExpanseResultTable> getPatientEcgExpanseResultTableById(Long id);

    List<PatientEcgExpanseResultTable> getAllPatientEcgExpanseResultTableByPatientId(Long patientId , StockBillState state);

    Optional<PatientEcgExpanseResultTable> getPatientEcgExpanseResultTableByEcgId(Long drugId);

    List<PatientEcgExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
