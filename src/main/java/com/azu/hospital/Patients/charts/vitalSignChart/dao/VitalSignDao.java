package com.azu.hospital.Patients.charts.vitalSignChart.dao;

import com.azu.hospital.Patients.charts.vitalSignChart.entity.VitalSign;

import java.util.List;
import java.util.Optional;

public interface VitalSignDao {
  VitalSign save(VitalSign vitalSign);

  VitalSign update(VitalSign vitalSign);

  Optional<VitalSign> findById(Long id);

  List<VitalSign> getAllVitalSignByPatientId(Long patientId);
}
