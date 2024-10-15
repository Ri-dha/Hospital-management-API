package com.azu.hospital.Patients.charts.postDischargeInstructions.dao;

import com.azu.hospital.Patients.charts.postDischargeInstructions.entity.PostDischargeInstructionsChart;

import java.util.List;
import java.util.Optional;

public interface PostDischargeInstructionsDao {
  PostDischargeInstructionsChart createNewChart(PostDischargeInstructionsChart chart);

  PostDischargeInstructionsChart updateExistsChart(PostDischargeInstructionsChart update);

  Optional<PostDischargeInstructionsChart> findChartById(Long chartId);

  List<PostDischargeInstructionsChart> getAllCharts(Long patientId);

}
