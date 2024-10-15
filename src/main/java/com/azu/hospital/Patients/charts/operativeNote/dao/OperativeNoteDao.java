package com.azu.hospital.Patients.charts.operativeNote.dao;

import com.azu.hospital.Patients.charts.operativeNote.entity.OperativeNote;

import java.util.List;
import java.util.Optional;

public interface OperativeNoteDao {


    OperativeNote createNewChart(OperativeNote operativeNote);

    OperativeNote updateExistChart(OperativeNote operativeNote);

    Optional<OperativeNote> findChartById(Long chartId);

    List<OperativeNote> getAllCharts(Long patientId);
}
