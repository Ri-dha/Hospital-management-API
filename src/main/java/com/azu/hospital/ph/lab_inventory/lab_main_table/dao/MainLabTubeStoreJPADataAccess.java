package com.azu.hospital.ph.lab_inventory.lab_main_table.dao;


import com.azu.hospital.ph.lab_inventory.lab_main_table.entity.MainLabTubeStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("MainLabTubeStoreJpa")
public class MainLabTubeStoreJPADataAccess implements MainLabTubeStoreDao {

    private final MainLabTubeStoreRepository repository;

    @Autowired
    public MainLabTubeStoreJPADataAccess(MainLabTubeStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public MainLabTubeStore createMainLapTubeStore(MainLabTubeStore request) {
       return  repository.save(request);
    }

    @Override
    public void updateMainLapTubeStore(MainLabTubeStore update) {
        repository.save(update);
    }

    @Override
    public void deleteMainLapTubeStore(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MainLabTubeStore> findMainLapTubeStoreById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<MainLabTubeStore> getAllMainLapTubeStore(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long countMainlabtube() {
        return repository.countMainLabTubeStore();
    }
}
