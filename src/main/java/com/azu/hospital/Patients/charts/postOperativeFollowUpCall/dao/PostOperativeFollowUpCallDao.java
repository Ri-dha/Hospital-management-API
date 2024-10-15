package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dao;


import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PatientExperiencing;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.entities.PostOperativeFollowUpCall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostOperativeFollowUpCallDao {

  PostOperativeFollowUpCall createNewChart(PostOperativeFollowUpCall chart);

  PostOperativeFollowUpCall updateExistsChart(PostOperativeFollowUpCall update);

  Optional<PostOperativeFollowUpCall> findChartById(Long chartId);

  List<PostOperativeFollowUpCall> getAllCharts(Long patientId);
}
