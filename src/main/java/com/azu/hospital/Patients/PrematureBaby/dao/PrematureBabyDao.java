package com.azu.hospital.Patients.PrematureBaby.dao;

import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PrematureBabyDao {

    void createPrematureBaby(PrematureBaby prematureBaby);

    void updatePrematureBaby(PrematureBaby prematureBaby);

    void deletePrematureBabyById(Long id);

    Page<PrematureBaby> findAllPrematureBaby(Pageable pageable);

    Optional<PrematureBaby> findPrematureBabyById(Long id);

    Page<PrematureBaby> findPrematureBabyByWardId(Long wardId, Pageable pageable);

    Optional<PrematureBaby> findPrematureBabyByBedId(Long bedId);
    List<PrematureBaby> findPrematureBabyByPatientId(Long patientId);



}
