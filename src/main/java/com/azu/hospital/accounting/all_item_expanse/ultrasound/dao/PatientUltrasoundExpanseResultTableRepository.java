package com.azu.hospital.accounting.all_item_expanse.ultrasound.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.radiology.entity.PatientRadiologyExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.ultrasound.entity.PatientUltrasoundExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import com.azu.hospital.utils.enums.radiology.RadiologyTypeEnum;
import com.azu.hospital.utils.enums.ultrasound.UltrasoundTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientUltrasoundExpanseResultTableRepository extends JpaRepository<PatientUltrasoundExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientUltrasoundExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientUltrasoundExpanseResultTable> findAllByPatientId(@Param("patientId") Long patientId, @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientUltrasoundExpanseResultTable d WHERE d.ultrasoundId = :patientId ")
    Optional<PatientUltrasoundExpanseResultTable> findPatientDrugsExpanseResultTableByDrugId(@Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientUltrasoundExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientUltrasoundExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(Long patientId);


    @Query("SELECT d FROM PatientUltrasoundExpanseResultTable d " +
            "WHERE d.ultrasoundId = :ultrasoundId AND d.patient.id = :patientId  AND d.isArchived = false")
    Optional<PatientUltrasoundExpanseResultTable> findPatientUltrasoundExpanseResultTableByRadiologyIdAndPatientId(
            @Param("ultrasoundId") Long ultrasoundId, @Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientUltrasoundExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientUltrasoundExpanseResultTable pd " +
            "WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientUltrasoundExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
