package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler.entity.DrugRequestHandler;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("DrugRequestHandlerJpa")
public class DrugRequestHandlerJpaDataAccess implements DrugRequestHandlerDao {
    private final DrugRequestHandlerRepository repository;

    @Autowired
    public DrugRequestHandlerJpaDataAccess(DrugRequestHandlerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DrugRequestHandler> addNewDrugRequest(List<DrugRequestHandler> request) {
        return repository.saveAll(new ArrayList<>(request));
    }

    @Override
    public void updateDrugRequest(DrugRequestHandler update) {
        repository.save(update);
    }

    @Override
    public void updateDrugRequestHandler(DrugRequestHandler drugRequestHandler) {
        repository.save(drugRequestHandler);
    }

    @Override
    public void deleteDrugRequestHandlerById(Long id) {
        repository.deleteById(id);
    }


    @Override
    public Optional<DrugRequestHandler> getRequestById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<DrugRequestHandler> getAllRequestsAccordingToUpdateAtOrCreateAtWithSorting() {
        return repository.getAllRequestsAccordingToUpdateAtOrCreateAtWithSorting();
    }

    @Override
    public Page<DrugRequestHandler> getAllRequestsAccordingTo(Long userId, Long drugId,
                                                              UnitInventoryRequestEnum requestStatus, Pageable pageable) {
        return repository.getAllRequestsAccordingTo(
                 userId,  drugId,  requestStatus,  pageable
        );
    }

    @Override
    public Page<DrugRequestHandler> getAllRequestsByStatusAndLastUpdateAsd(UnitInventoryRequestEnum status, Pageable pageable) {
        return repository.getAllRequestsByStatusAndLastUpdateAsd(status, pageable);
    }

    @Override
    public List<DrugRequestHandler> getAllDrugsRequestByPatientId(Long patientId) {
        return repository.getAllRequestsByPatientId(patientId);
    }

    @Override
    public List<BillsDtoSum<String>> sumAllPatientQuantityDrugsRequestByPatientId(Long patientId) {
        return repository.sumAllDrugsItemQuantityWithSameDrugIdForSamePatientId(patientId);
    }

    @Override
    public List<DrugRequestHandler> getAllReceivedDrugsRequestByPatentId(Long patientId) {
        return repository.findAllReceivedDrugsRequestByPatientId(patientId);
    }

    @Override
    public Long repositoryCount() {
        return repository.repositoryCount();
    }


}
