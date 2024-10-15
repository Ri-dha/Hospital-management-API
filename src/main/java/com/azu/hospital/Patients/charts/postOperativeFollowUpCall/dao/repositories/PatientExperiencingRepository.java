package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.repositories;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PatientExperiencingRepository extends JpaRepository<PatientExperiencing, Long> {
  @Query("SELECT p FROM PatientExperiencing p WHERE p.postOperativeFollowUpCall.id = :chartId ")
  List<PatientExperiencing> findAllExperiencingByChartId(@Param("chartId") Long chartId);
}
