package com.azu.hospital.accounting.patientBill.dao;

import com.azu.hospital.accounting.patientBill.entity.PatientBill;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface PatientBillRepository extends JpaRepository<PatientBill , Long> {

    Page<PatientBill> getAllByPatientIdOrderByUpdatedAtDescCreatedAtDesc(Long id , Pageable pageable);
}
