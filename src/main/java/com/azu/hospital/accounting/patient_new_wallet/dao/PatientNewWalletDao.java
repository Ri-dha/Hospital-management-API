package com.azu.hospital.accounting.patient_new_wallet.dao;

import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public sealed interface PatientNewWalletDao permits PatientNewWalletJpaDataAccess {

    void addNewPatientNewWallet(PatientNewWallet request);

    void updatePatientNewWallet(PatientNewWallet update);

    Optional<PatientNewWallet> getPatientNewWalletById(Long id);

    Page<PatientNewWallet> getAllPatientNewWallet(Pageable pageable);

    Long totalPatientNewWalletCount();




}
