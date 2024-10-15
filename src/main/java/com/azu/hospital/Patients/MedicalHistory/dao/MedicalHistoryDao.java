package com.azu.hospital.Patients.MedicalHistory.dao;

import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryDao {

    Optional<MedicalHistory> getMedicalHistoryById(Long id);
    MedicalHistory insertMedicalHistory(MedicalHistory medicalHistory);

    List<MedicalHistory> medicalHistoryByPatientId(Long patientId);


    Optional<MedicalHistory> getLastByPatientId(Long patientId);

    void updateMedicalHistory(MedicalHistory medicalHistory);

    boolean existsMedicalHistoryById(Long id);

}
