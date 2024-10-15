package com.azu.hospital.accounting.all_item_expanse.radiology.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientRadiologyExpanseResultTableRepository extends JpaRepository<PatientRadiologyExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientRadiologyExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientRadiologyExpanseResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                               @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientRadiologyExpanseResultTable d WHERE d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientRadiologyExpanseResultTable>findPatientDrugsExpanseResultTableByDrugId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientRadiologyExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientRadiologyExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientRadiologyExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);


    @Query("SELECT pd FROM PatientRadiologyExpanseResultTable pd " +
            "WHERE pd.radiologyId = :radiologyId AND pd.patient.id = :patientId AND pd.isArchived = false")
    Optional<PatientRadiologyExpanseResultTable> findPatientRadiologyExpanseResultTableByRadiologyIdAndPatientId(
            @Param("radiologyId") Long radiologyId, @Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientRadiologyExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientRadiologyExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
