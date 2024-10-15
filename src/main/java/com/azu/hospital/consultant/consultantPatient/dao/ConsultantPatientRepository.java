package com.azu.hospital.consultant.consultantPatient.dao;

import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Transactional
public interface ConsultantPatientRepository extends JpaRepository<ConsultantPatient , Long> {
    List<ConsultantPatient> getAllByDoctorIdOrderByCreatedAtDesc(Long doctorId);

}
