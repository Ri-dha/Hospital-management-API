package com.azu.hospital.accounting.patient_wallet.patient_transaction_list.dao;

import com.azu.hospital.accounting.patient_wallet.patient_transaction_list.entity.PatientTransactionList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientTransactionRepository extends JpaRepository<PatientTransactionList, Long> {

    @Query("select p from PatientTransactionList p where p.patientWallet.patient.id = :patientId order by p.createdAt desc")
    List<PatientTransactionList> findAllByPatientId(@Param("patientId") Long patientId);
}
