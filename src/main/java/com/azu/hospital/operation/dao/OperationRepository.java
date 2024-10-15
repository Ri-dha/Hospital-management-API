package com.azu.hospital.operation.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.operation.entity.Operation;
import com.azu.hospital.utils.enums.operation.OperationStates;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {


    @Query("SELECT o FROM Operation o where o.patient.id = :patientId order by o.operationDate")
    Page<Operation> getAllByPatientId(@Param("patientId") Long patientId, Pageable pageable);


    @Query("SELECT o FROM Operation o " +
            "LEFT JOIN MedicalHistory m ON o.patient.id = m.patient.id " +
            "AND m.id = (SELECT MAX(mh.id) FROM MedicalHistory mh WHERE mh.patient.id = o.patient.id) " +
            "WHERE EXTRACT(DATE FROM o.operationDate) = EXTRACT(DATE FROM CAST(:date AS TIMESTAMP)) " +
            "AND o.state IN :states " +
            "ORDER BY o.state ,o.triage, m.triage, o.createdAt")
    Page<Operation> getAllByDate(@Param("date") Instant date, @Param("states") List<OperationStates> states, Pageable pageable);


    @Query("SELECT new com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum" +
            "(Max(s.surgical.id), CAST(count (s) AS integer), s.surgical.surgicalName  , Max(s.surgical.price)," +
            " CAST(Max(s.surgical.price) * CAST(count(s) AS integer) AS BIGDECIMAL ) ) FROM" +
            " Operation s WHERE s.patient.id = :patientId and s.state in :operationStates GROUP BY s.surgical.surgicalName")
    List<BillsDtoSum<String>> getAllWithSum(@Param("patientId") Long patientId, List<OperationStates> operationStates);

    @Query("SELECT o FROM Operation o where o.patient.id = :patientId and o.state in :operationStates and o.isArchived = false ")
    List<Operation> getAllCompletedByPatientId(Long patientId, List<OperationStates> operationStates);

    @Query("SELECT count (o) FROM Operation o where o.surgical.surgicalName =:type and o.patient.id = :patientId and o.state = com.azu.hospital.utils.enums.operation.OperationStates.BeforeRecover and o.isArchived = false ")
    Integer countAllByPatientId(Long patientId, String type);


    @Query("SELECT COUNT(o) FROM Operation o WHERE o.state = :state AND EXTRACT(MONTH FROM CAST(o.createdAt AS TIMESTAMP)) = EXTRACT(MONTH FROM CAST(:month AS TIMESTAMP))")
    Long countAllByStateAndMonth(@Param("state") OperationStates state, @Param("month") Instant month);

    @Query("SELECT COUNT(o) FROM Operation o WHERE o.state = :state AND EXTRACT(DATE FROM CAST(o.operationDate AS TIMESTAMP)) = EXTRACT(DATE FROM CAST(:operationDate AS TIMESTAMP))")
    Long countByStateAndDate(OperationStates state, Instant operationDate);

    @Query("SELECT COUNT(o) FROM Operation o WHERE o.state IN :states AND EXTRACT(DATE FROM CAST(o.operationDate AS TIMESTAMP)) = EXTRACT(DATE FROM CAST(:operationDate AS TIMESTAMP))")
    Long countByListOfStatesAndDate(List<OperationStates> states, Instant operationDate);

    @Query("SELECT o FROM Operation o WHERE o.state IS NULL or o.state IN :states")
    Page<Operation> getAllByListOfStates(List<OperationStates> states,Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.id IN (SELECT o.patient.id FROM Operation o WHERE o.state IN :states)")
    Page<Patient> getAllPatientWhoHasOperationAndStateIsBeforeInOperation(List<OperationStates> states, Pageable pageable);

}

