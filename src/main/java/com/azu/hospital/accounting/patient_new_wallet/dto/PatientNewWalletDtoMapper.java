package com.azu.hospital.accounting.patient_new_wallet.dto;

import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientNewWalletDtoMapper implements Function<PatientNewWallet, PatientNewWalletDto> {
    @Override
    public PatientNewWalletDto apply(PatientNewWallet patientNewWallet) {
        return new PatientNewWalletDto(
                patientNewWallet.getId(),
                patientNewWallet.getOperationId(),
                patientNewWallet.getUserId(),
                patientNewWallet.getPatientIdm(),
                patientNewWallet.getPatientName(),
                patientNewWallet.getOperationName(),
                patientNewWallet.getUserName(),
                patientNewWallet.getNote(),
                patientNewWallet.getOperationCost(),
                patientNewWallet.getDoctorPercentage()
        );
    }
}
