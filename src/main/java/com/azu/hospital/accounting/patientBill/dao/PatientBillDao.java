package com.azu.hospital.accounting.patientBill.dao;

import com.azu.hospital.accounting.patientBill.entity.PatientBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface PatientBillDao {

    void createPatientBill(PatientBill patientBill);
    void updatePatientBill(PatientBill patientBill);
    Optional<PatientBill> findPatientBillById(Long id);
    Page<PatientBill> getPatientBillByPatientId(Long patientId , Pageable pageable);
    Page<PatientBill> getAll(Pageable pageable);

}
