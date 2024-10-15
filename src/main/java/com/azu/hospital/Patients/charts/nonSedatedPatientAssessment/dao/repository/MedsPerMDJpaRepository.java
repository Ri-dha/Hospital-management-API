package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dao.repository;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.entities.MedsPerMDEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface MedsPerMDJpaRepository extends JpaRepository<MedsPerMDEntity, Long> {
}
