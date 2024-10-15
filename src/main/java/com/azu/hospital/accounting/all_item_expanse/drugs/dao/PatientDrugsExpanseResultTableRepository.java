package com.azu.hospital.accounting.all_item_expanse.drugs.dao;

import com.azu.hospital.accounting.all_item_expanse.drugs.entity.PatientDrugsExpanseResultTable;
import com.azu.hospital.utils.enums.StockBillState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientDrugsExpanseResultTableRepository extends JpaRepository<PatientDrugsExpanseResultTable, Long> {

    @Query(value = "SELECT pd FROM PatientDrugsExpanseResultTable pd WHERE pd.patient.id = :patientId" +
            " AND pd.state IS NULL OR pd.state = :state")
    List<PatientDrugsExpanseResultTable> findAllByPatientId(@Param("patientId") Long patientId
            , @Param("state") StockBillState state);




    @Query("SELECT pd FROM PatientDrugsExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = false")
    List<PatientDrugsExpanseResultTable> findAllPatientExpansesExpanseResultTableByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT p FROM PatientDrugsExpanseResultTable p WHERE p.patient.id = :patientId AND p.isArchived = false")
    Optional<PatientDrugsExpanseResultTable> findPatientDrugsExpanseResultTableByPatientId( @Param("patientId") Long patientId);

    @Query("SELECT pd FROM PatientDrugsExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedTrue(Long patientId);

    @Query("SELECT pd FROM PatientDrugsExpanseResultTable pd WHERE pd.patient.id = :patientId AND pd.isArchived = true")
    List<PatientDrugsExpanseResultTable> findAllByPatientIdAndIsArchivedIsTrueAnd(Long patientId);

}
