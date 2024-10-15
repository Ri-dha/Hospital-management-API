package com.azu.hospital.Patients.charts.nursingAssessment.dao;

import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;

import java.util.List;
import java.util.Optional;

public interface NursingAssessmentChartDao {

    NursingAssessmentChart createNewChart(NursingAssessmentChart request);

    NursingAssessmentChart updateExistChart(NursingAssessmentChart update);

    Optional<NursingAssessmentChart> findChartById(Long chartId);

    List<NursingAssessmentChart> getAllCharts(Long patientId);


}
