package com.azu.hospital.ph.main_data_store.item_archive.dao;

import com.azu.hospital.ph.main_data_store.item_archive.entity.DrugItemArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("DrugItemArchiveJpa")
public class DrugItemArchiveJpaDataAccess implements DrugItemArchiveDao{

    private final DrugItemArchiveRepository repository;

    @Autowired
    public DrugItemArchiveJpaDataAccess(DrugItemArchiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DrugItemArchive> findByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public void addDrugToArchives(DrugItemArchive request) {
        repository.save(request);
    }
}
