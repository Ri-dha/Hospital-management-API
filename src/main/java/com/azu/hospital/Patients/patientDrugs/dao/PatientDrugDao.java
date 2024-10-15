package com.azu.hospital.Patients.patientDrugs.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PatientDrugDao {
  List<PatientDrug> create(List<PatientDrug> drugs);

  void createPatientDrug(PatientDrug drug);
  PatientDrug update(PatientDrug update);
  Optional<PatientDrug> getDrugById(Long drugId);
  List<PatientDrug> getAllDrugsByPatientId(Long patientId);


}
