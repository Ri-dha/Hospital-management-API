package com.azu.hospital.newPh.Billing.dao;

import com.azu.hospital.newPh.Billing.BillingType;
import com.azu.hospital.newPh.Billing.entity.MedicinesBilling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface MedicinesBillingRepository extends JpaRepository<MedicinesBilling, Long> {


    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.patient.id = :patientId")
    Optional<MedicinesBilling> getMedicinesBillingByPatientId(Long patientId);

    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.medicinesRequestsList.id = :medicinesRequestsListId")
    Optional<MedicinesBilling> getMedicinesBillingByMedicinesRequestsListId(Long medicinesRequestsListId);

    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.patient.id = :patientId")
    Page<MedicinesBilling> getMedicinesBillingListByPatientId(Long patientId, Pageable pageable);


    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.patient IS NULL")
    Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNull(Pageable pageable);

    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.patient IS NOT NULL")
    Page<MedicinesBilling> getAllMedicinesBillingWherePatientIsNotNull(Pageable pageable);

    @Query("SELECT mb FROM MedicinesBilling mb WHERE mb.billingType = :type")
    Page<MedicinesBilling> getMedicinesBillingListByType(BillingType type, Pageable pageable);

}
