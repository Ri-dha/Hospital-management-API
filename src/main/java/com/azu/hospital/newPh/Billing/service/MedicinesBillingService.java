package com.azu.hospital.newPh.Billing.service;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.dao.PatientDao;
import com.azu.hospital.newPh.Billing.dao.MedicinesBillingDao;
import com.azu.hospital.newPh.Billing.dto.MedicinesBillingDto;
import com.azu.hospital.newPh.Billing.dto.MedicinesBillingDtoMapper;
import com.azu.hospital.newPh.Billing.entity.MedicinesBilling;
import com.azu.hospital.newPh.Billing.requests.MedicinesBillingCreateRequest;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.dao.MedicinesRequestsListDao;
import com.azu.hospital.newPh.MedicinsRequests.MedicinesRequestsList.entity.MedicinesRequestsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicinesBillingService {

    private final MedicinesBillingDao medicinesBillingDao;
    private final MedicinesBillingDtoMapper medicinesBillingDtoMapper;
    private final PatientDao patientDao;

    private final MedicinesRequestsListDao medicinesRequestsListDao;


    @Autowired
    public MedicinesBillingService(MedicinesBillingDao medicinesBillingDao, MedicinesBillingDtoMapper medicinesBillingDtoMapper, PatientDao patientDao, MedicinesRequestsListDao medicinesRequestsListDao) {
        this.medicinesBillingDao = medicinesBillingDao;
        this.medicinesBillingDtoMapper = medicinesBillingDtoMapper;
        this.patientDao = patientDao;
        this.medicinesRequestsListDao = medicinesRequestsListDao;
    }

    public void createMedicinesBilling(MedicinesBillingCreateRequest request) {

        MedicinesBilling medicinesBilling = new MedicinesBilling();
        MedicinesRequestsList medicinesRequestsList = medicinesRequestsListDao.getMedicinesRequestsListById(request.getMedicinesRequestsListId())
                .orElseThrow(() -> new IllegalStateException("MedicinesRequestsList with id " + request.getMedicinesRequestsListId() + " not found"));
        medicinesBilling.setMedicinesRequestsList(medicinesRequestsList);

        if (request.getPatientId() != null) {
            Patient patient = patientDao.getPatientById(request.getPatientId())
                    .orElseThrow(() -> new IllegalStateException("Patient with id " + request.getPatientId() + " not found"));
            medicinesBilling.setPatient(patient);
        }

        medicinesBilling.setFinalPrice(request.getFinalPrice());
        medicinesBilling.setBillingType(request.getBillingType());
        medicinesBillingDao.insertMedicinesBilling(medicinesBilling);
    }


    public MedicinesBillingDto getMedicinesBillingById(Long id) {
        MedicinesBilling medicinesBilling = medicinesBillingDao.getMedicinesBillingById(id)
                .orElseThrow(() -> new IllegalStateException("MedicinesBilling with id " + id + " not found"));
        return medicinesBillingDtoMapper.apply(medicinesBilling);
    }

    public MedicinesBillingDto getMedicinesBillingByPatientId(Long patientId) {
        MedicinesBilling medicinesBilling = medicinesBillingDao.getMedicinesBillingByPatientId(patientId)
                .orElseThrow(() -> new IllegalStateException("MedicinesBilling with patientId " + patientId + " not found"));
        return medicinesBillingDtoMapper.apply(medicinesBilling);
    }

    public MedicinesBillingDto getMedicinesBillingByMedicinesRequestsListId(Long medicinesRequestsListId) {
        MedicinesBilling medicinesBilling = medicinesBillingDao.getMedicinesBillingByMedicinesRequestsListId(medicinesRequestsListId)
                .orElseThrow(() -> new IllegalStateException("MedicinesBilling with medicinesRequestsListId " + medicinesRequestsListId + " not found"));
        return medicinesBillingDtoMapper.apply(medicinesBilling);
    }

    public void updateMedicinesBilling(Long id, MedicinesBillingCreateRequest request) {

        boolean changed = false;

        MedicinesBilling medicinesBilling = medicinesBillingDao.getMedicinesBillingById(id)
                .orElseThrow(() -> new IllegalStateException("MedicinesBilling with id " + id + " not found"));

    }

}
