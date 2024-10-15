package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PatientTransactionDtoMapper implements Function<PatientTransactionList, PatientTransactionDto> {
    @Override
    public PatientTransactionDto apply(PatientTransactionList patientTransactionList) {
        return new PatientTransactionDto(
                patientTransactionList.getId(),
                patientTransactionList.getAmount(),
                patientTransactionList.getType(),
                patientTransactionList.getCreatedAt(),
                patientTransactionList.getUpdatedAt()
        );
    }

}
