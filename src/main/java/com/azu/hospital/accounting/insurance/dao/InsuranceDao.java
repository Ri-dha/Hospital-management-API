package com.azu.hospital.accounting.insurance.dao;

import com.azu.hospital.accounting.insurance.entity.Insurance;
import com.azu.hospital.accounting.insurance.request.InsuranceRequest;

import java.util.Optional;

public interface InsuranceDao {

    void createInsurance(Insurance insurance);

    Optional<Insurance> getInsuranceById(Long insuranceId);

    void updateInsurance(Insurance insurance);

    Optional<Insurance> getInsuranceByPatientId(Long patientId);

}
