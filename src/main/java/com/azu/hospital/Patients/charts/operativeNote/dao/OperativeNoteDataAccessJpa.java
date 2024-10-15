package com.azu.hospital.Patients.charts.operativeNote.dao;


import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("operativeNoteJpa")
public class OperativeNoteDataAccessJpa implements OperativeNoteDao {
    private final OperativeNoteRepository repository;


    public OperativeNoteDataAccessJpa(OperativeNoteRepository repository) {
        this.repository = repository;
    }


    @Override
    public OperativeNote createNewChart(OperativeNote operativeNote) {
        return repository.save(operativeNote);
    }

    @Override
    public OperativeNote updateExistChart(OperativeNote operativeNote) {
        return repository.save(operativeNote);
    }

    @Override
    public Optional<OperativeNote> findChartById(Long chartId) {
        return repository.findById(chartId);
    }


    @Override
    public List<OperativeNote> getAllCharts(Long patientId) {
        return repository.findAllByPatientId(patientId);
    }
}
