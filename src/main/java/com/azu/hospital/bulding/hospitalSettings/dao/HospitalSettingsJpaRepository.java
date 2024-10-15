package com.azu.hospital.bulding.hospitalSettings.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface HospitalSettingsJpaRepository extends JpaRepository<HospitalSettings, Long> {
}
