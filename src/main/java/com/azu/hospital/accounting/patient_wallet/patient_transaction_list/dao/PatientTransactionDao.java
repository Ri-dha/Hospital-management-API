package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dao;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dto.PatientTransactionDto;
import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientTransactionDao {

    void addTransaction(PatientTransactionList patientTransactionList);

    List<PatientTransactionList> getPatientTransactionList(Long patientId);
}
