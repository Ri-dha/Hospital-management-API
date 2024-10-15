package com.azu.hospital.Patients.PrematureBaby.dao;


import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PrematureBabyRepository extends JpaRepository<PrematureBaby, Long> {


    @Query("SELECT p FROM PrematureBaby p WHERE p.ward.wardId = ?1")
    Page<PrematureBaby> findPrematureBabyByWardId(Long wardId, Pageable pageable);

    @Query("SELECT p FROM PrematureBaby p WHERE p.bed.id = ?1")
    Optional<PrematureBaby> findPrematureBabyByBedId(Long bedId);


    @Query("SELECT p FROM PrematureBaby p WHERE p.patient.id = ?1")
    List<PrematureBaby> findPrematureBabyByPatientId(Long patientId);


}
