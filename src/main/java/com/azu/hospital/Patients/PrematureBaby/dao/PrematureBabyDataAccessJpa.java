package com.azu.hospital.Patients.PrematureBaby.dao;


import com.azu.hospital.Patients.PrematureBaby.entity.PrematureBaby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PrematureBabyDataJpa")
public class PrematureBabyDataAccessJpa implements PrematureBabyDao {


    private final PrematureBabyRepository repository;

    @Autowired
    public PrematureBabyDataAccessJpa(@Qualifier("prematureBabyRepository") PrematureBabyRepository prematureBabyRepository) {
        this.repository = prematureBabyRepository;
    }


    @Override
    public void createPrematureBaby(PrematureBaby prematureBaby) {
        repository.save(prematureBaby);
    }

    @Override
    public void updatePrematureBaby(PrematureBaby prematureBaby) {
        repository.save(prematureBaby);
    }

    @Override
    public void deletePrematureBabyById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<PrematureBaby> findAllPrematureBaby(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<PrematureBaby> findPrematureBabyById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<PrematureBaby> findPrematureBabyByWardId(Long wardId, Pageable pageable) {
        return repository.findPrematureBabyByWardId(wardId, pageable);
    }

    @Override
    public Optional<PrematureBaby> findPrematureBabyByBedId(Long bedId) {
        return repository.findPrematureBabyByBedId(bedId);
    }

    @Override
    public List<PrematureBaby> findPrematureBabyByPatientId(Long patientId) {
        return repository.findPrematureBabyByPatientId(patientId);
    }
}
