package com.azu.hospital.accounting.all_item_expanse.drugs.dao;

import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientDrugsExpanseResultTableDao {

    void createPatientDrugsExpanseResultTable(PatientDrugsExpanseResultTable request);

    void updatePatientDrugsExpanseResultTable(PatientDrugsExpanseResultTable update);

    List<PatientDrugsExpanseResultTable> getAllPatientDrugsExpanseResultTableByPatientId(Long patientId , StockBillState state);

    List<PatientDrugsExpanseResultTable> getAllPatientExpansesExpanseResultTableByPatientId(Long patientId);


    Optional<PatientDrugsExpanseResultTable> getPatientDrugsExpanseResultTableByDrugIdAndPriceAndPatientId(Long drugId, BigDecimal price, Long patientId);

    List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId);
}
