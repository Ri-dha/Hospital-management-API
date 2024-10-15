package com.azu.hospital.Patients.charts.base_chart.dao;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseChartsDao {

    Page<BaseCharts> getAllChartsByPatientId(Long patientId, Pageable pageable);

    Optional<BaseCharts> getChartsById(Long id);

    Long countAllItems();
    
}
