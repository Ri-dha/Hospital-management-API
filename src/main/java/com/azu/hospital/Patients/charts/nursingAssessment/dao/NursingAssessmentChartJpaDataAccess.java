package com.azu.hospital.Patients.charts.nursingAssessment.dao;

import com.azu.hospital.Patients.charts.nursingAssessment.entity.NursingAssessmentChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("NursingAssessmentJpa")
public class NursingAssessmentChartJpaDataAccess implements NursingAssessmentChartDao {

    private final NursingAssessmentChartRepository assessmentChartRepository;

    @Autowired
    public NursingAssessmentChartJpaDataAccess(NursingAssessmentChartRepository assessmentChartRepository) {
        this.assessmentChartRepository = assessmentChartRepository;
    }


    @Override
    public NursingAssessmentChart createNewChart(NursingAssessmentChart request) {
        return assessmentChartRepository.save(request);
    }

    @Override
    public NursingAssessmentChart updateExistChart(NursingAssessmentChart update) {
        return assessmentChartRepository.save(update);
    }

    @Override
    public Optional<NursingAssessmentChart> findChartById(Long chartId) {
        return assessmentChartRepository.findById(chartId);
    }

    @Override
    public List<NursingAssessmentChart> getAllCharts(Long patientId) {
        return assessmentChartRepository.findAllByPatientId(patientId);
    }
}
