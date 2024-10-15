package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface DrugRequestHandlerListRepository extends JpaRepository<DrugRequestHandlerList, Long> {

    @Query("SELECT  drhl FROM DrugRequestHandlerList  drhl " +
            "ORDER BY COALESCE(drhl.updateAt, drhl.createAt) ")
    Page<DrugRequestHandlerList> getAllRequestsListAccordingToUpdateAtOrCreateAtWithSorting(Pageable pageable);


    @Query("SELECT drhl FROM DrugRequestHandlerList drhl WHERE " +
            "(:patientName IS NULL OR drhl.patient.patientData.fullName LIKE concat('%', lower(:patientName), '%')) " +
            " and drhl.isCompleted = false " +
            "ORDER BY COALESCE(drhl.updateAt, drhl.createAt)   ")
    Page<DrugRequestHandlerList> getAllWithFilter(@Param("patientName") String patientName, Pageable pageable);


    @Query("SELECT drhl FROM DrugRequestHandlerList  drhl WHERE drhl.patient.id = :patientId ORDER BY " +
            "COALESCE(drhl.updateAt, drhl.createAt)")
    List<DrugRequestHandlerList> getAllByPatientId(@Param("patientId") Long patientId);



    @Query("SELECT drhl FROM DrugRequestHandlerList drhl WHERE drhl.isCompleted = false ")
    Long countAllDrugRequestHandlerList();


}



