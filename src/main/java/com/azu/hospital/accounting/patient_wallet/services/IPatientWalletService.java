package com.azu.hospital.accounting.patient_wallet.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.accounting.patient_wallet.dto.PatientWalletDto;
import com.azu.hospital.accounting.patient_wallet.request.PatientWalletRequest;

public interface IPatientWalletService {

    void createNewPatientWallet(Patient patient);
    void addBalance(PatientWalletRequest request);

    void payCutBalance(PatientWalletRequest request);

    PatientWalletDto getPatientWallet(Long patientId);


}
