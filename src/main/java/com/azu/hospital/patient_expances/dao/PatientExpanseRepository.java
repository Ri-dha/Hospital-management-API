package com.azu.hospital.patient_expances.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PatientExpanseRepository extends JpaRepository<PatientExpanse, Long> {



    @Query("SELECT r FROM PatientExpanse r LEFT JOIN  r.patientExpanseLists pl WHERE pl.patient.id = :patientId  AND  r.isArchived = false")
    List<PatientExpanse> findAllReceivedDrugsRequestByPatientId(@Param("patientId") Long patientId);


}
