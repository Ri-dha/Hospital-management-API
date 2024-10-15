package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.repositories;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PostOperativeFollowUpCallJpaRepository
        extends JpaRepository<PostOperativeFollowUpCall, Long> {
  @Query("SELECT chart FROM PostOperativeFollowUpCall chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<PostOperativeFollowUpCall> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
