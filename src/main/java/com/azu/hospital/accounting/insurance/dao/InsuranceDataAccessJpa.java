package com.azu.hospital.accounting.insurance.dao;

import com.azu.hospital.accounting.insurance.entity.Insurance;
import com.azu.hospital.accounting.insurance.request.InsuranceRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("insuranceJpa")
public class InsuranceDataAccessJpa implements InsuranceDao{

    private final InsuranceRepository repository;

    public InsuranceDataAccessJpa(InsuranceRepository repository) {
        this.repository = repository;
    }


    @Override
    public void createInsurance(Insurance insurance) {
         repository.save(insurance);
    }

    @Override
    public Optional<Insurance> getInsuranceById(Long billId) {
        return repository.findById(billId);
    }

    @Override
    public void updateInsurance(Insurance insurance) {
        repository.save(insurance);
    }

    @Override
    public Optional<Insurance> getInsuranceByPatientId(Long patientId) {
        return repository.findByBillId(patientId);
    }


}
