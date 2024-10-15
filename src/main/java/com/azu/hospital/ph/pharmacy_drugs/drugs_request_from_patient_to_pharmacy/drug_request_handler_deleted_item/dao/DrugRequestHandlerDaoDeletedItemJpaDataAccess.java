package com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler_deleted_item.dao;

import com.azu.hospital.ph.pharmacy_drugs.drugs_request_from_patient_to_pharmacy.drug_request_handler_deleted_item.entity.DrugRequestHandlerDeletedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(" DrugRequestHandlerDaoDeletedItemJpa")
public class DrugRequestHandlerDaoDeletedItemJpaDataAccess implements DrugRequestHandlerDeletedItemDao{
    private final DrugRequestHandlerDaoDeletedItemRepository repository;
    @Autowired
    public DrugRequestHandlerDaoDeletedItemJpaDataAccess(DrugRequestHandlerDaoDeletedItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addDrugRequestHandlerDeleteItem(DrugRequestHandlerDeletedItem deletedItem) {
        repository.save(deletedItem);
    }
}
