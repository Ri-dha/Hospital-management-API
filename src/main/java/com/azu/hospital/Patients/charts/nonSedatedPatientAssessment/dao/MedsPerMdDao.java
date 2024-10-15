package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;

import java.util.Set;

public interface MedsPerMdDao {
  Set<MedsPerMDEntity> createAll(Set<MedsPerMDEntity> meds);
}
