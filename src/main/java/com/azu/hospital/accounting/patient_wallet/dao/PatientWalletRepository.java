package com.azu.hospital.accounting.patient_wallet.dao;

import com.azu.hospital.accounting.patient_wallet.entity.PatientWallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface PatientWalletRepository extends JpaRepository<PatientWallet, Long> {


    @Query("SELECT w FROM PatientWallet w WHERE w.patient.id = :patientId")
    Optional<PatientWallet> getPatientWalletByPatientId(@Param("patientId") Long patientId);

}
