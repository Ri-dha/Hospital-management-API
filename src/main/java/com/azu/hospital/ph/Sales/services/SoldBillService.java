package com.azu.hospital.ph.Sales.services;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.Sales.Request.SoldBillRegistrationRequest;
import com.azu.hospital.ph.Sales.dao.SoldBillDao;
import com.azu.hospital.ph.Sales.entity.SoldBill;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SoldBillService {

    private final SoldBillDao soldBillDao;
    private final PatientDao patientDao;



    @Autowired
    public SoldBillService(@Qualifier("soldJpa") SoldBillDao soldBillDao, PatientDao patientDao) {
        this.soldBillDao = soldBillDao;
        this.patientDao = patientDao;
    }

    @Transactional
    public Map<String, Long> createNewSoldBill(SoldBillRegistrationRequest request){

        Patient patient = patientDao.getPatientById(request.getPatientId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Patient not found"
                        )
                );
        SoldBill bill = new SoldBill(
                request.getFullPrice()
        );
        bill.setPatient(patient);
         soldBillDao.createNewSoldBill(bill);
         Map<String, Long> result = new HashMap<>();
         result.put("SolidBillId",bill.getBillId());



        return result;
    }




}
