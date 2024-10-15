package com.azu.hospital.Patients.charts.nursingObservation.dao.Observation;

import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;

import java.util.List;

public interface ObservationDao {
    void createNewObservationChart(ObservationTime chart);


    List<ObservationTime> getAllObservationCharts(Long chartId);

    void createNewListObservationTime(List<ObservationTime> chart);
}
