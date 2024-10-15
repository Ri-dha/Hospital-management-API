package com.azu.hospital.consultant.consultantPatient.vitalSign.dao;

import com.azu.hospital.consultant.consultantPatient.vitalSign.entity.ConsultantVitalSign;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ConsultantVitalSignRepository extends JpaRepository<ConsultantVitalSign, Long> {


    List<ConsultantVitalSign> getAllByConsultantPatientIdOrderByCreatedAtDesc(Long patientId);

}
