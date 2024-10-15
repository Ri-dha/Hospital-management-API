package com.azu.hospital.Patients.charts.nursingObservation.dao.Observation;


import com.azu.hospital.Patients.charts.nursingObservation.entity.ObservationTime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ObservationJpa")
public class ObservationDataJpa implements ObservationDao {
    private final ObservationRepository observationRepository;

    public ObservationDataJpa(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }


    @Override
    public void createNewObservationChart(ObservationTime chart) {
        observationRepository.save(chart);
    }

    @Override
    public List<ObservationTime> getAllObservationCharts(Long chartId) {
        return observationRepository.findAllByChartId(chartId);
    }

    @Override
    public void createNewListObservationTime(List<ObservationTime> chart) {
        observationRepository.saveAll(chart);
    }
}
