package com.azu.hospital.accounting.all_item_expanse.opreation.dao;

import com.azu.hospital.accounting.all_item_expanse.opreation.entity.PatientOperationExpanseResultTable;
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
public interface PatientOperationExpanseResultTableRepository extends JpaRepository<PatientOperationExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientOperationExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientOperationExpanseResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                               @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientOperationExpanseResultTable d WHERE d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientOperationExpanseResultTable>findPatientOperationsExpanseResultTableByOperationId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientOperationExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientOperationExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query(value = "SELECT d FROM PatientOperationExpanseResultTable d WHERE d.operationId = :operationId AND d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientOperationExpanseResultTable> findPatientOperationExpanseResultTableByOperationIdAndPatientId(
            @Param("operationId") Long operationId, @Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientOperationExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientOperationExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientOperationExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
