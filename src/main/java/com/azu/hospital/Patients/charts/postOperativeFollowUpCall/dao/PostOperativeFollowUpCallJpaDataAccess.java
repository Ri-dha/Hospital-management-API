package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.repositories.PatientExperiencingRepository;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao.repositories.PostOperativeFollowUpCallJpaRepository;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("PostOperativeFollowUpCallJpaDataAccess")
public class PostOperativeFollowUpCallJpaDataAccess implements PostOperativeFollowUpCallDao{
  private final PostOperativeFollowUpCallJpaRepository repository;

  @Autowired
  public PostOperativeFollowUpCallJpaDataAccess(
          PostOperativeFollowUpCallJpaRepository repository
  ) {
    this.repository = repository;
  }

  @Override
  public PostOperativeFollowUpCall createNewChart(PostOperativeFollowUpCall chart) {
    return repository.save(chart);
  }

  @Override
  public PostOperativeFollowUpCall updateExistsChart(PostOperativeFollowUpCall update) {
    return repository.save(update);
  }

  @Override
  public Optional<PostOperativeFollowUpCall> findChartById(Long chartId) {
    return repository.findById(chartId);
  }

  @Override
  public List<PostOperativeFollowUpCall> getAllCharts(Long patientId) {
      return repository.findAllChartsByPatientId(patientId);
  }
}
