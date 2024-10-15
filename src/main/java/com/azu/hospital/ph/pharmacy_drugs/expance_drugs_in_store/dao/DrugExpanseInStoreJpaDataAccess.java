package com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.dao;

import com.azu.hospital.ph.pharmacy_drugs.expance_drugs_in_store.entity.DrugExpanseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository("DrugExpanseInStoreJpa")
public class DrugExpanseInStoreJpaDataAccess implements DrugExpanseInStoreDao{

    private final DrugExpanseTableRepository repository;
    @Autowired
    public DrugExpanseInStoreJpaDataAccess(DrugExpanseTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DrugExpanseTable> getExpanseInStoreTableById(Long expanseId) {
        return repository.findById(expanseId);
    }

    @Override
    public void addDrugsToExpanseInStore(DrugExpanseTable request) {
        repository.save(request);
    }

    @Override
    public void updateDrugsInExpanseStore(DrugExpanseTable update) {
        repository.save(update);
    }

    @Override
    public Optional<DrugExpanseTable> getExpanseInStoreTableByDrugIdOfMainTable(Long drugId) {
        return repository.getExpanseInStoreTableByDrugIdOfMainTable(drugId);
    }

    @Override
    public Page<DrugExpanseTable> getAllExpanseDrugs(String drugTradeName, String drugScientificName, Instant updateAt, String date, Pageable pageable) {
        return repository.getAllExpanseDrugs(drugTradeName,drugScientificName, updateAt, date, pageable);
    }
}
