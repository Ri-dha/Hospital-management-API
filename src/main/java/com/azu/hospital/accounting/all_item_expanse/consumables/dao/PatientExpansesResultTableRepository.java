package com.azu.hospital.accounting.all_item_expanse.consumables.dao;

import com.azu.hospital.accounting.all_item_expanse.consumables.entity.PatientsExpansesResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientExpansesResultTableRepository extends JpaRepository<PatientsExpansesResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientsExpansesResultTable pd WHERE pd.patient.id = :patientId " +
            "AND pd.state IS NULL OR pd.state = :state")
    List<PatientsExpansesResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                        @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientsExpansesResultTable d WHERE d.patient.id = :patientId and d.isArchived = false")
    Optional<PatientsExpansesResultTable>findPatientExpansesExpanseResultTableByExpansesId(@Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientsExpansesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientsExpansesResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientsExpansesResultTable pd WHERE pd.itemId = :consumableId AND pd.itemPrice = :price AND pd.patient.id = :patientId AND pd.isArchived = false")
    Optional<PatientsExpansesResultTable> findPatientExpansesResultTableByItemIdAndPriceAndPatientId(
            @Param("consumableId")Long consumableId, @Param("price") BigDecimal price, @Param("patientId") Long patientId);


    @Query("SELECT pd FROM PatientsExpansesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);


    @Query("SELECT pd FROM PatientsExpansesResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientsExpansesResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
