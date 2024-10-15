package com.azu.hospital.Patients.charts.nusringCarePlan.dao;

import com.azu.hospital.Patients.charts.nusringCarePlan.entity.NursingCarePlan;

import java.util.List;
import java.util.Optional;

public interface NursingCarePlanDao {

  NursingCarePlan createNewChart(NursingCarePlan nursingCarePlan);

  NursingCarePlan updateExistChart(NursingCarePlan nursingCarePlan);

  Optional<NursingCarePlan> findChartById(Long chartId);

  List<NursingCarePlan> getAllCharts(Long patientId);

}
