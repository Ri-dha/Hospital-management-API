package com.azu.hospital.Patients.MedicalHistory.dao;

import com.azu.hospital.Patients.MedicalHistory.entity.MedicalHistory;
import com.azu.hospital.Patients.Patient.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("MedicalHistoryRepo")
public class MedicalHistoryJpaDataAccess implements MedicalHistoryDao{

    private final MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    public MedicalHistoryJpaDataAccess(MedicalHistoryRepository medicalHistoryRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    @Override
    public Optional<MedicalHistory> getMedicalHistoryById(Long id) {
        return medicalHistoryRepository.findById(id);
    }

    @Override
    public MedicalHistory insertMedicalHistory(MedicalHistory medicalHistory) {
        return  medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public List<MedicalHistory> medicalHistoryByPatientId(Long patientId) {
        return medicalHistoryRepository.findMedicalHistoriesByPatientIdOrderByUpdateAtDescDaysOffWrittenDesc(patientId);
    }

    @Override
    public Optional<MedicalHistory> getLastByPatientId(Long patientId) {
        return medicalHistoryRepository.getLastByPatientId(patientId);
    }


    @Override
    public void updateMedicalHistory(MedicalHistory medicalHistory) {
      medicalHistoryRepository.save(medicalHistory);
    }

    @Override
    public boolean existsMedicalHistoryById(Long id) {
        return medicalHistoryRepository.existsById(id);
    }
}
