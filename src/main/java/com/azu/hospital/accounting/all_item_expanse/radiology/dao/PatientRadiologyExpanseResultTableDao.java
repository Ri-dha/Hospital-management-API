package com.azu.hospital.accounting.all_item_expanse.radiology.dao;

import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRadiologyExpanseResultTableDao {

    void createPatientRadiologyExpanseResultTable(PatientRadiologyExpanseResultTable request);

    void updatePatientRadiologyExpanseResultTable(PatientRadiologyExpanseResultTable update);

    Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableById(Long id);

    List<PatientRadiologyExpanseResultTable> getAllPatientRadiologyExpanseResultTableByPatientId(Long patientId , StockBillState state);

    Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableByRadiologyId(Long patientId);

    List<PatientRadiologyExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientRadiologyExpanseResultTable> getPatientRadiologyExpanseResultTableByRadiologyIdAndPatientId(Long radiologyId, Long patientId);
    List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}

