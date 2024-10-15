package com.azu.hospital.Patients.charts.base_chart.dao;

import com.azu.hospital.Patients.charts.base_chart.entity.BaseCharts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("BaseChartsJpa")
public class BaseChartsJpaDataAccess implements BaseChartsDao {

    private final BaseChartsRepository repository;

    @Autowired
    public BaseChartsJpaDataAccess(BaseChartsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<BaseCharts> getAllChartsByPatientId(Long patientId, Pageable pageable) {
        return repository.findAllByPatientIdWithCoalesceOrderBy(patientId, pageable);
    }

    @Override
    public Optional<BaseCharts> getChartsById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long countAllItems() {
        return repository.count();
    }
}
