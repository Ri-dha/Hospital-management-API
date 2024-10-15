package com.azu.hospital.accounting.all_item_expanse.Labs.dao;

import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientLabExpanseResultTableDao {

    void createPatientLabExpanseResultTable(PatientLabExpanseResultTable request);

    void updatePatientLabExpanseResultTable(PatientLabExpanseResultTable update);

    Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableById(Long id);

    List<PatientLabExpanseResultTable> getAllPatientLabExpanseResultTableByPatientId(Long patientId , StockBillState state);

    Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableByLabId(Long patientId);

    List<PatientLabExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientLabExpanseResultTable> getPatientLabExpanseResultTableByLabIdAndPatientId(Long labId, Long patientId);


    List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
