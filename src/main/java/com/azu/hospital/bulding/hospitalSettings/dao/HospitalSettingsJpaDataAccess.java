package com.azu.hospital.bulding.hospitalSettings.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("HospitalSettingsJpaDataAccess")
public class HospitalSettingsJpaDataAccess implements HospitalSettingsDao{

  private final HospitalSettingsJpaRepository repository;

  @Autowired
  public HospitalSettingsJpaDataAccess(HospitalSettingsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public HospitalSettings save(HospitalSettings settings) {
    return repository.save(settings);
  }

  @Override
  public HospitalSettings update(HospitalSettings update) {
    return repository.save(update);
  }

  @Override
  public Optional<HospitalSettings> getHospitalSettings() {
    return repository.findAll().stream().findFirst();
  }
}
