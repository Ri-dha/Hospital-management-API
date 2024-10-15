package com.azu.hospital.Patients.patientDrugs.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("PatientDrugJpaDataAccess")
public class PatientDrugJpaDataAccess implements PatientDrugDao {
  private final PatientDrugJpaRepository repository;

  @Autowired
  public PatientDrugJpaDataAccess(PatientDrugJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<PatientDrug> create(List<PatientDrug> drugs) {
    return repository.saveAll(new ArrayList<>(drugs));
  }

  @Override
  public void createPatientDrug(PatientDrug drug) {
    repository.save(drug);
  }

  @Override
  public PatientDrug update(PatientDrug update) {
    return repository.save(update);
  }

  @Override
  public Optional<PatientDrug> getDrugById(Long drugId) {
    return repository.findById(drugId);
  }

  @Override
  public List<PatientDrug> getAllDrugsByPatientId(Long patientId) {
    return repository.findAllByPatientId(patientId);
  }
}
