package com.azu.hospital.accounting.insurance.dao;

import com.azu.hospital.accounting.insurance.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    @Query("SELECT i FROM Insurance i WHERE i.patient.id = :patientId")
    Optional<Insurance> findByBillId(@Param("patientId") Long patientId);
}
