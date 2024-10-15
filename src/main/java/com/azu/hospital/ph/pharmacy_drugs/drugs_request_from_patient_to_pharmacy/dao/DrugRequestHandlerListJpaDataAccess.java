package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.dao;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.entity.DrugRequestHandlerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DrugRequestHandlerListJpa")
public class DrugRequestHandlerListJpaDataAccess implements DrugRequestHandlerListDao{

    private final DrugRequestHandlerListRepository repository;

    @Autowired
    public DrugRequestHandlerListJpaDataAccess(DrugRequestHandlerListRepository repository) {
        this.repository = repository;
    }

    @Override
    public DrugRequestHandlerList addDrugRequestHandlerList(DrugRequestHandlerList drugRequestHandlerList) {
        return repository.save(drugRequestHandlerList);
    }

    @Override
    public void updateDrugRequestHandlerList(DrugRequestHandlerList drugRequestHandlerList) {
        repository.save(drugRequestHandlerList);
    }

    @Override
    public Optional<DrugRequestHandlerList> getDrugRequestHandlerListById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<DrugRequestHandlerList> getDrugRequestHandlerListAllWithFilter(String patientName, Pageable pageable) {
        return repository.getAllWithFilter(patientName,pageable);
    }


    @Override
    public List<DrugRequestHandlerList> getDrugRequestHandlerListByPatientId(Long patientId) {
        return repository.getAllByPatientId(patientId);
    }

    @Override
    public Page<DrugRequestHandlerList> getDrugRequestHandlerListAccordingToUpdatedAt(Pageable pageable) {
        return repository.getAllRequestsListAccordingToUpdateAtOrCreateAtWithSorting(pageable);
    }

    @Override
    public Long countAllDrugRequestHandlerList() {
        return repository.countAllDrugRequestHandlerList();
    }


}
