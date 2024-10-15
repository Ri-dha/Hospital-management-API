package com.azu.hospital.bulding.hospitalSettings.dao;

import java.util.Optional;

public interface HospitalSettingsDao {
  HospitalSettings save(HospitalSettings settings);
  HospitalSettings update(HospitalSettings update);
  Optional<HospitalSettings> getHospitalSettings();
}
