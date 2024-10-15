package com.azu.hospital.newPh.Billing.dao;

import com.azu.hospital.newPh.Billing.BillingType;
import com.azu.hospital.newPh.Billing.entity.MedicinesBilling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface MedicinesBillingDao {

    void insertMedicinesBilling(MedicinesBilling medicinesBilling);
    void updateMedicinesBilling(MedicinesBilling medicinesBilling);
    void deleteMedicinesBilling(Long id);

    Optional<MedicinesBilling> getMedicinesBillingById(Long id);

    Optional<MedicinesBilling> getMedicinesBillingByPatientId(Long patientId);

    Optional<MedicinesBilling> getMedicinesBillingByMedicinesRequestsListId(Long medicinesRequestsListId);

    Page<MedicinesBilling> getMedicinesBillingList(Pageable pageable);

    Page<MedicinesBilling> getMedicinesBillingListByPatientId(Long patientId, Pageable pageable);


    Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNull(Pageable pageable);

    Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNotNull(Pageable pageable);

    Page<MedicinesBilling> getMedicinesBillingListByType(BillingType type, Pageable pageable);

}
