package com.azu.hospital.accounting.patient_wallet.dao;

import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PatientWalletDataJpa")
public class PatientWalletDataJpsAccess implements PatientWalletDao {



    private final PatientWalletRepository patientWalletRepository;

    public PatientWalletDataJpsAccess(PatientWalletRepository patientWalletRepository) {
        this.patientWalletRepository = patientWalletRepository;
    }

    @Override
    public PatientWallet createNewPatientWallet(PatientWallet patientWallet) {
       return patientWalletRepository.save(patientWallet);
    }

    @Override
    public void updatePatientWallet(PatientWallet patientWallet) {
       patientWalletRepository.save(patientWallet);
    }

    @Override
    public Optional<PatientWallet> getPatientWalletByPatientId(Long patientId) {
        return patientWalletRepository.getPatientWalletByPatientId(patientId);
    }

    @Override
    public Optional<PatientWallet> findById(Long id) {
        return patientWalletRepository.findById(id);
    }
}
