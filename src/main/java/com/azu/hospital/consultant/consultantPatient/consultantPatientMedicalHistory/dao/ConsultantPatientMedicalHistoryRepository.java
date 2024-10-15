package com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.dao;

import com.azu.hospital.consultant.consultantPatient.consultantPatientMedicalHistory.entity.ConsultantPatientMedicalHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ConsultantPatientMedicalHistoryRepository extends JpaRepository<ConsultantPatientMedicalHistory, Long> {


    ConsultantPatientMedicalHistory findFirstByConsultantPatientIdOrderByCreatedAtDescUpdatedAtDesc(Long patientId);

    List<ConsultantPatientMedicalHistory> getAllByConsultantPatientIdOrderByCreatedAtDesc(Long patientId);

}
