package com.azu.hospital.Patients.charts.nusringCarePlan.dao;

import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NursingCarePlanRepository  extends JpaRepository<NursingCarePlan,Long> {

  List<NursingCarePlan> findAllByPatientId(@Param("patientId") Long patientId);



}
