package com.azu.hospital.accounting.all_item_expanse.Labs.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.accounting.all_item_expanse.Labs.entity.PatientLabExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.ecg.entity.PatientEcgExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientLabExpanseResultTableRepository extends JpaRepository<PatientLabExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientLabExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientLabExpanseResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                         @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientLabExpanseResultTable d WHERE d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientLabExpanseResultTable>findPatientLabsExpanseResultTableByPatientId(@Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientLabExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientLabExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientLabExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.labTestId = :labId AND pd.isArchived = false")
    Optional<PatientLabExpanseResultTable> findPatientLabExpanseResultTableByLabIdAndPatientId(Long labId, Long patientId);


    @Query("SELECT pd FROM PatientLabExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientLabExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientLabExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
