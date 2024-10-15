package com.azu.hospital.accounting.all_item_expanse.ultrasound.dao;

import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientUltrasoundExpanseResultTableDao {

    void createPatientUltrasoundExpanseResultTable(PatientUltrasoundExpanseResultTable request);

    void updatePatientUltrasoundExpanseResultTable(PatientUltrasoundExpanseResultTable update);

    Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableById(Long id);

    List<PatientUltrasoundExpanseResultTable> getAllPatientUltrasoundExpanseResultTableByPatientId(Long patientId , StockBillState state);

    Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableByRadiologyId(Long ultrasoundId);

    List<PatientUltrasoundExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);

    Optional<PatientUltrasoundExpanseResultTable> getPatientUltrasoundExpanseResultTableByRadiologyIdAndPatientId( Long ultrasoundId, Long patientId);

    List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}

