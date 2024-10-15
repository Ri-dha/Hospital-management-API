package com.azu.hospital.Patients.charts.postDischargeInstructions.dao;

import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PostDischargeInstructionsJpaRepository
        extends JpaRepository<PostDischargeInstructionsChart, Long> {
  @Query("SELECT chart FROM PostDischargeInstructionsChart chart WHERE chart.patient.id = :patientId ORDER BY coalesce(chart.createdAt,chart.updatedAt)DESC")
  List<PostDischargeInstructionsChart> findAllChartsByPatientId(@Param("patientId") Long patientId);
}
