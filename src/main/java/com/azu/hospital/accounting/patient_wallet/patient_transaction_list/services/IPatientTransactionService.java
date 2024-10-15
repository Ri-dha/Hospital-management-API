package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.services;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto.PatientTransactionDto;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.request.PatientTransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPatientTransactionService {

    void addTransaction(PatientTransactionRequest request);

    List<PatientTransactionDto> getPatientTransactionList(Long patientId);

}
