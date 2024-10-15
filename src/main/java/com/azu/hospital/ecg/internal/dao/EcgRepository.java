package com.azu.hospital.ecg.internal.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ecg.internal.entity.Ecg;
import com.azu.hospital.utils.enums.TestDestination;
import com.azu.hospital.utils.enums.ecg.EcgStates;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface EcgRepository extends JpaRepository<Ecg, Long> {


    @Query("SELECT e FROM Ecg e WHERE " +
            "(:destination IS NULL OR e.testDestination != :destination) " +
            "AND (:patientName IS NULL OR LOWER(e.patient.patientData.fullName) LIKE LOWER(CONCAT('%', :patientName, '%'))) " +
            "AND  e.state IN :state " +
            "ORDER BY e.createdAt DESC")
    Page<Ecg> getAllWithFilter(
            @Param("destination") TestDestination destination,
            @Param("patientName") String patientName,
            @Param("state") List<EcgStates> State,
            Pageable pageable);


    @Query("select e from Ecg e WHERE e.patient.id = :patientId ORDER BY e.createdAt DESC")
    Page<Ecg> getAllByPatientId(@Param("patientId") Long patientId,Pageable pageable);


    @Query("SELECT e FROM Ecg e where e.consultantPatient.id = :patientId")
    List<Ecg> getAllByConsultantPatientId(Long patientId);


    @Query("SELECT e FROM Ecg e where e.testDestination = :testDestination")
    Page<Ecg> getAllByTestDestination(TestDestination testDestination, Pageable pageable);

    @Query("SELECT e FROM Ecg e where e.patient.id = :patientId and e.state = com.azu.hospital.utils.enums.ecg.EcgStates.Completed and e.isArchived = false")
    List<Ecg> getAllCompleteByPatientId(Long patientId);


    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum(Max(s.ecgBill.id), CAST(count (s) AS integer)" +
            ", 'ecg ' , Max(s.ecgBill.price), CAST(Max(s.ecgBill.price) * CAST(count (s) AS integer) AS BIGDECIMAL ) " +
            ") FROM Ecg s WHERE s.patient.id = :patientId and s.state= com.azu.hospital.utils.enums.ecg.EcgStates.Completed GROUP BY s.patient.id")
    List<BillsDtoSum<String>> findSumByPatientId(Long patientId);

    @Query("SELECT count (s) FROM Ecg s WHERE s.patient.id = :patientId and s.state= com.azu.hospital.utils.enums.ecg.EcgStates.Completed and s.isArchived = false")
    Integer countAllByPatientId(Long patientId);
}
