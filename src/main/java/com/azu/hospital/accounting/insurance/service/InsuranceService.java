package com.azu.hospital.accounting.insurance.service;


import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.accounting.insurance.dao.InsuranceDao;
import com.azu.hospital.accounting.insurance.dto.InsuranceDto;
import com.azu.hospital.accounting.insurance.dto.InsuranceDtoMapper;
import com.azu.hospital.accounting.insurance.entity.Insurance;
import com.azu.hospital.accounting.insurance.request.InsuranceRequest;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InsuranceService {
    private final InsuranceDao insuranceDao;
    private final PatientDao patientDao;

    private final InsuranceDtoMapper insuranceDtoMapper;

    public InsuranceService(InsuranceDao insuranceDao, PatientDao patientDao, InsuranceDtoMapper insuranceDtoMapper) {
        this.insuranceDao = insuranceDao;
        this.patientDao = patientDao;
        this.insuranceDtoMapper = insuranceDtoMapper;
    }

    @Transactional
    public void createInsurance(Long patientId, InsuranceRequest request) {
        Patient patient = patientDao.getPatientById(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        ("resourceNotFound")
                )
        );

        Insurance insurance = new Insurance();
        insurance.setInsuranceAmount(request.insuranceAmount());
        insurance.setDepositAmount(request.depositAmount());
        insurance.setPatient(patient);
        insuranceDao.createInsurance(insurance);
    }

    public InsuranceDto getInsuranceById(Long patientId){
        return insuranceDao.getInsuranceByPatientId(patientId).
                map(insuranceDtoMapper).
                orElse(null);
    }

    public void updateInsurance(Long patientId, InsuranceRequest request) {

        Insurance insurance = insuranceDao.getInsuranceByPatientId(patientId).orElseThrow(
                () -> new ResourceNotFoundException(
                        ("resourceNotFound")
                )
        );

        if (request.insuranceAmount() != null) {
            insurance.setInsuranceAmount(request.insuranceAmount());
        }
        if (request.depositAmount() != null) {
            insurance.setDepositAmount(request.depositAmount());
        }
        insuranceDao.updateInsurance(insurance);
    }
}
