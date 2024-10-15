package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dao;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PatientTransactionDao")
public class PatientTransactionDataJpa implements PatientTransactionDao {


    private final PatientTransactionRepository patientTransactionRepository;

    public PatientTransactionDataJpa(PatientTransactionRepository patientTransactionRepository) {
        this.patientTransactionRepository = patientTransactionRepository;
    }


    @Override
    public void addTransaction(PatientTransactionList patientTransactionList) {
        patientTransactionRepository.save(patientTransactionList);
    }

    @Override
    public List<PatientTransactionList> getPatientTransactionList(Long patientId) {
        return patientTransactionRepository.findAllByPatientId(patientId);
    }
}
