package com.azu.hospital.Patients.charts.nursingObservation.dao;

import com.azu.hospital.Patients.charts.nursingObservation.entity.NursingObservation;

import java.util.List;
import java.util.Optional;

public interface NursingObservationDao {

    void createNewChart(NursingObservation chart);
    void updateExistsChart(NursingObservation update);

    Optional<NursingObservation> findChartById(Long chartId);

    List<NursingObservation> getAllCharts();

    List<NursingObservation> getAllChartsByPatientId(Long patientId);


}
