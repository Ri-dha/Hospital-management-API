package com.azu.hospital.lab_collection.lab_test_main_table.dao;

import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LabTestMainTableDao {

    void addNewTestToMainTable(LabTestMainTableForMainTestName request);


    void addListNewTestToMainTable(List<LabTestMainTableForMainTestName> request);

    void updateExistTestFromMainTable(LabTestMainTableForMainTestName update);

    Optional<LabTestMainTableForMainTestName> getTestFromMainTableById(Long testId);

    Optional<LabTestMainTableForMainTestName> getTestFromMainTableByTestName(String testName);

    Page<LabTestMainTableForMainTestName> getAllTestesFromMainTableWithNameFilter(String testName, Pageable pageable);

    List<LabTestMainTableForMainTestName> getAllLabTests();

    Boolean existsAllByLabTestNameName(String LabTestName);

    Page<LabTestMainTableForMainTestName> getAllWithPrice(Pageable pageable);
}
