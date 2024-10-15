package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dao;

import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity.ConsultantPatientMedicalHistory;

import java.util.List;
import java.util.Optional;

public interface ConsultantPatientMedicalHistoryDao {


    ConsultantPatientMedicalHistory getLastMedicalHistory(Long patientId);
    Optional<ConsultantPatientMedicalHistory> findById(Long id);

    List<ConsultantPatientMedicalHistory> getAllMedicalHistory(Long patientId);
    ConsultantPatientMedicalHistory newConsultantPatientMedicalHistory(ConsultantPatientMedicalHistory medicalHistory);
    void updateConsultantPatientMedicalHistory(ConsultantPatientMedicalHistory medicalHistory);

}
