package com.azu.hospital.newPh.Billing.dao;


import com.azu.hospital.newPh.Billing.BillingType;
import com.azu.hospital.newPh.Billing.entity.MedicinesBilling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("MedicinesBillingRepositoryJpa")
public class MedicinesBillingDataAccessJpa  implements MedicinesBillingDao{

    private final MedicinesBillingRepository medicinesBillingRepository;

    @Autowired
    public MedicinesBillingDataAccessJpa(MedicinesBillingRepository medicinesBillingRepository) {
        this.medicinesBillingRepository = medicinesBillingRepository;
    }

    @Override
    public void insertMedicinesBilling(MedicinesBilling medicinesBilling) {
        medicinesBillingRepository.save(medicinesBilling);
    }

    @Override
    public void updateMedicinesBilling(MedicinesBilling medicinesBilling) {
        medicinesBillingRepository.save(medicinesBilling);
    }

    @Override
    public void deleteMedicinesBilling(Long id) {
        medicinesBillingRepository.deleteById(id);
    }

    @Override
    public Optional<MedicinesBilling> getMedicinesBillingById(Long id) {
        return medicinesBillingRepository.findById(id);
    }

    @Override
    public Optional<MedicinesBilling> getMedicinesBillingByPatientId(Long patientId) {
        return medicinesBillingRepository.getMedicinesBillingByPatientId(patientId);
    }

    @Override
    public Optional<MedicinesBilling> getMedicinesBillingByMedicinesRequestsListId(Long medicinesRequestsListId) {
        return medicinesBillingRepository.getMedicinesBillingByMedicinesRequestsListId(medicinesRequestsListId);
    }

    @Override
    public Page<MedicinesBilling> getMedicinesBillingList(Pageable pageable) {
        return medicinesBillingRepository.findAll(pageable);
    }

    @Override
    public Page<MedicinesBilling> getMedicinesBillingListByPatientId(Long patientId, Pageable pageable) {
        return medicinesBillingRepository.getMedicinesBillingListByPatientId(patientId, pageable);
    }

    @Override
    public Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNull(Pageable pageable) {
        return medicinesBillingRepository.getAllMedicinesBillingWherePatientIsNull(pageable);
    }

    @Override
    public Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNotNull(Pageable pageable) {
        return medicinesBillingRepository.getAllMedicinesBillingWherePatientIsNotNull(pageable);
    }

    @Override
    public Page<MedicinesBilling> getMedicinesBillingListByType(BillingType type, Pageable pageable) {
        return medicinesBillingRepository.getMedicinesBillingListByType(type, pageable);
    }
}
