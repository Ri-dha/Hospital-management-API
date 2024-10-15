package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dao;

import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity.ConsultantPatientMedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("consultantPatientMedicalHistoryJpa")
public class ConsultantPatientMedicalHistoryJpa implements ConsultantPatientMedicalHistoryDao {


    private final ConsultantPatientMedicalHistoryRepository repository;

    @Autowired
    public ConsultantPatientMedicalHistoryJpa(ConsultantPatientMedicalHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ConsultantPatientMedicalHistory getLastMedicalHistory(Long patientId) {
        return repository.findFirstByConsultantPatientIdOrderByCreatedAtDescUpdatedAtDesc(patientId);
    }

    @Override
    public Optional<ConsultantPatientMedicalHistory> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ConsultantPatientMedicalHistory> getAllMedicalHistory(Long patientId) {
        return repository.getAllByConsultantPatientIdOrderByCreatedAtDesc(patientId);
    }

    @Override
    public ConsultantPatientMedicalHistory newConsultantPatientMedicalHistory(ConsultantPatientMedicalHistory medicalHistory) {
        return repository.save(medicalHistory);
    }

    @Override
    public void updateConsultantPatientMedicalHistory(ConsultantPatientMedicalHistory medicalHistory) {
        repository.save(medicalHistory);
    }
}
