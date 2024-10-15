package com.azu.hospital.Patients.patientDrugs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientDrugJpaRepository extends JpaRepository<PatientDrug, Long> {
  // TODO: 10/3/2023 Replace drugId with patient.id
  @Query("SELECT pd FROM PatientDrug pd WHERE pd.patient.id = :patientId")
  List<PatientDrug> findAllByPatientId(@Param("patientId") Long patientId);
}
