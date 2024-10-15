package com.azu.hospital.lab_collection.lab_test_main_table.dao;

import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("LabTestMainTableJpa")
public class LabTestMainTableJpaDataAccess implements LabTestMainTableDao{

    private final LabTestMainTableRepository repository;

    @Autowired
    public LabTestMainTableJpaDataAccess(LabTestMainTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewTestToMainTable(LabTestMainTableForMainTestName request) {
        repository.save(request);
    }

    @Override
    public void addListNewTestToMainTable(List<LabTestMainTableForMainTestName> request) {
        repository.saveAll(new ArrayList<>(request));
    }

    @Override
    public void updateExistTestFromMainTable(LabTestMainTableForMainTestName update) {
            repository.save(update);
    }

    @Override
    public Optional<LabTestMainTableForMainTestName> getTestFromMainTableById(Long testId) {
        return repository.findById(testId);
    }

    @Override
    public Optional<LabTestMainTableForMainTestName> getTestFromMainTableByTestName(String testName) {
        return repository.findByTestName(testName);
    }

    @Override
    public Page<LabTestMainTableForMainTestName> getAllTestesFromMainTableWithNameFilter(String testName, Pageable pageable) {
        return repository.getAllTestesFromMainTableWithNameFilter(testName, pageable);
    }

    @Override
    public List<LabTestMainTableForMainTestName> getAllLabTests() {
        return repository.findAll();
    }

    @Override
    public Boolean existsAllByLabTestNameName(String LabTestName) {
        return repository.existsAllByLabTestName(LabTestName);
    }

    @Override
    public Page<LabTestMainTableForMainTestName> getAllWithPrice(Pageable pageable) {
        return repository.getAllWithPrice(pageable);
    }
}
