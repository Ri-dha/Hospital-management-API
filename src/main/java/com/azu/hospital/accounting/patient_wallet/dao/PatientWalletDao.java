package com.azu.hospital.accounting.patient_wallet.dao;

import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;

import java.util.Optional;

public interface PatientWalletDao {


    PatientWallet createNewPatientWallet(PatientWallet patientWallet);

    void updatePatientWallet(PatientWallet patientWallet);

    Optional<PatientWallet> getPatientWalletByPatientId(Long patientId);

    Optional<PatientWallet> findById(Long id);

}
