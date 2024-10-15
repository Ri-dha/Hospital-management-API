package com.azu.hospital.Patients.MedicalHistory.dao;

import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory , Long> {

    List<MedicalHistory> findMedicalHistoriesByPatientIdOrderByUpdateAtDescDaysOffWrittenDesc(Long patientId);


    @Query("SELECT m FROM MedicalHistory m WHERE m.patient.id = :patientId " +
            "ORDER BY COALESCE(m.updateAt, m.daysOffWritten) DESC limit 1")
    Optional<MedicalHistory> getLastByPatientId(@Param("patientId") Long patientId);
}
