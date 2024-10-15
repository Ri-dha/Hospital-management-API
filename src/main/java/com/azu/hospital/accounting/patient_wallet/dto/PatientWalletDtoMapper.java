package com.azu.hospital.accounting.patient_wallet.dto;


import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientWalletDtoMapper implements Function<PatientWallet , PatientWalletDto > {
    @Override
    public PatientWalletDto apply(PatientWallet patientWallet) {
        return new PatientWalletDto(
                patientWallet.getId(),
                patientWallet.getBalance()
        );
    }
}
