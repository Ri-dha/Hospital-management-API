package com.azu.hospital.accounting.all_item_expanse.other_consumbles.dao;

import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
import com.azu.hospital.accounting.all_item_expanse.other_consumbles.entity.PatientOtherConsumablesResultTable;
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
public interface PatientOtherConsumablesTableRepository extends JpaRepository<PatientOtherConsumablesResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientOtherConsumablesResultTable pd WHERE pd.patient.id = :patientId " +
            "AND pd.state IS NULL OR pd.state = :state")
    List<PatientOtherConsumablesResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                        @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientOtherConsumablesResultTable d WHERE d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientOtherConsumablesResultTable>findPatientExpansesExpanseResultTableByExpansesId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientOtherConsumablesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientOtherConsumablesResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT d FROM PatientOtherConsumablesResultTable d WHERE d.itemId = :itemId AND d.patient.id = :patientId  AND d.isArchived = false")
    Optional<PatientOtherConsumablesResultTable> findPatientExpansesExpanseResultTableByExpansesIdAndPatientId(Long itemId, Long patientId);

    @Query("SELECT d FROM PatientOtherConsumablesResultTable d WHERE d.itemId = :itemId AND d.patient.id = :patientId")
    Optional<PatientOtherConsumablesResultTable> findByNameAndPatientId(Long itemId, Long patientId);


    @Query("SELECT pd FROM PatientOtherConsumablesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientOtherConsumablesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientOtherConsumablesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
