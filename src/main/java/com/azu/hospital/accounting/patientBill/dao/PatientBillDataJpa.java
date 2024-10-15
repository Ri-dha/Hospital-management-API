package com.azu.hospital.accounting.patientBill.dao;

import com.azu.hospital.accounting.patientBill.entity.PatientBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PatientBillJpa")
public class PatientBillDataJpa implements PatientBillDao {

    private final PatientBillRepository repository;

    public PatientBillDataJpa(PatientBillRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createPatientBill(PatientBill patientBill) {
        repository.save(patientBill);
    }

    @Override
    public void updatePatientBill(PatientBill patientBill) {
        repository.save(patientBill);
    }

    @Override
    public Optional<PatientBill> findPatientBillById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<PatientBill> getPatientBillByPatientId(Long patientId , Pageable pageable) {
        return repository.getAllByPatientIdOrderByUpdatedAtDescCreatedAtDesc(patientId , pageable);
    }

    @Override
    public Page<PatientBill> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
