package com.azu.hospital.Patients.charts.burnDiagram.dao;

import com.azu.hospital.Patients.charts.burnDiagram.entities.BurnDiagramChart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BurnDiagramDao {
  BurnDiagramChart createNewChart(BurnDiagramChart chart);

  BurnDiagramChart updateExistsChart(BurnDiagramChart update);

  Optional<BurnDiagramChart> findChartById(Long chartId);

  List<BurnDiagramChart> getAllChartsByPatientId(Long patientId);

}
