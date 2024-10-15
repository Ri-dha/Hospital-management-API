package com.azu.hospital.accounting.all_item_expanse.ecg.dao;

import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
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
public interface PatientEcgExpanseResultTableRepository extends JpaRepository<PatientEcgExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientEcgExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientEcgExpanseResultTable>findAllByPatientId(@Param("patientId") Long patientId ,
                                                         @Param("state") StockBillState state);


    @Query(value = "SELECT d FROM PatientEcgExpanseResultTable d WHERE d.ecgId = :ecgId")
    Optional<PatientEcgExpanseResultTable>findPatientDrugsExpanseResultTableByDrugId(@Param("ecgId") Long ecgId);

    @Query("SELECT pd FROM PatientEcgExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientEcgExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(Long patientId);


    @Query("SELECT pd FROM PatientEcgExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientEcgExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true AND pd.dateOfPayment = :date")
    List<PatientEcgExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAndByDateOfPayment(Long patientId, LocalDate date);
}
