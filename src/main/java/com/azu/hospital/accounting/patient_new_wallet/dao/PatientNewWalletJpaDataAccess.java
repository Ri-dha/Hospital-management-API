package com.azu.hospital.accounting.patient_new_wallet.dao;

import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public non-sealed class PatientNewWalletJpaDataAccess implements PatientNewWalletDao{


    private final PatientNewWalletRepository repository;

    @Autowired
    public PatientNewWalletJpaDataAccess(PatientNewWalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewPatientNewWallet(PatientNewWallet request) {
        repository.save(request);
    }

    @Override
    public void updatePatientNewWallet(PatientNewWallet update) {
     repository.save(update);
    }

    @Override
    public Optional<PatientNewWallet> getPatientNewWalletById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<PatientNewWallet> getAllPatientNewWallet(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Long totalPatientNewWalletCount() {
        return repository.count();
    }
}
