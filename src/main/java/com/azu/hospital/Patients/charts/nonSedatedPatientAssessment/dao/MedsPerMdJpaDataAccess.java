package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.repository.MedsPerMDJpaRepository;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("MedsPerMdJpaDataAccess")
public class MedsPerMdJpaDataAccess implements MedsPerMdDao{

  private final MedsPerMDJpaRepository repository;

  @Autowired
  public MedsPerMdJpaDataAccess(MedsPerMDJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Set<MedsPerMDEntity> createAll(Set<MedsPerMDEntity> meds) {
    return new HashSet<>(repository.saveAll(meds));
  }
}
