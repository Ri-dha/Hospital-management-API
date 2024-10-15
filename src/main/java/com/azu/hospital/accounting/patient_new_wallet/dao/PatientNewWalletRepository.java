package com.azu.hospital.accounting.patient_new_wallet.dao;

import com.azu.hospital.accounting.patient_new_wallet.PatientNewWallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface PatientNewWalletRepository extends JpaRepository<PatientNewWallet, Long> {
}
