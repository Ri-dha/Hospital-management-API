package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DrugRequestHandlerListDao {

    DrugRequestHandlerList addDrugRequestHandlerList(DrugRequestHandlerList drugRequestHandlerList);
    void updateDrugRequestHandlerList(DrugRequestHandlerList drugRequestHandlerList);

    Optional<DrugRequestHandlerList> getDrugRequestHandlerListById(Long id);

    Page<DrugRequestHandlerList> getDrugRequestHandlerListAllWithFilter(String patientName, Pageable pageable);

    List<DrugRequestHandlerList> getDrugRequestHandlerListByPatientId(Long patientId);

    Page<DrugRequestHandlerList> getDrugRequestHandlerListAccordingToUpdatedAt(Pageable pageable);

    Long countAllDrugRequestHandlerList();
}
