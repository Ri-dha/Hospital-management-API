package com.azu.hospital.Patients.charts.vitalSignChart.dao;

import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("VitalSignJpaDataAccess")
public class VitalSignJpaDataAccess implements VitalSignDao {

  private final VitalSignJpaRepository repository;

  @Autowired
  public VitalSignJpaDataAccess(VitalSignJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public VitalSign save(VitalSign vitalSign) {
    return repository.save(vitalSign);
  }

  @Override
  public VitalSign update(VitalSign vitalSign) {
    return repository.save(vitalSign);
  }

  @Override
  public Optional<VitalSign> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public List<VitalSign> getAllVitalSignByPatientId(Long patientId) {
    return repository.getAllByPatientId(patientId);
  }
}
