package com.azu.hospital.lab_collection.lab_test_main_table.dao;

import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@Transactional
public interface LabTestMainTableRepository extends JpaRepository<LabTestMainTableForMainTestName, Long> {


    @Query("SELECT ltmt FROM LabTestMainTableForMainTestName ltmt WHERE (:testName IS NULL OR LOWER(ltmt.testName) LIKE LOWER(CONCAT('%', :testName, '%')))")
    Page<LabTestMainTableForMainTestName>   getAllTestesFromMainTableWithNameFilter(
            @Param("testName") String testName,
            Pageable pageable
    );

    @Query("SELECT ltmt FROM LabTestMainTableForMainTestName ltmt WHERE UPPER(TRIM(ltmt.testName)) LIKE UPPER(TRIM(:testName))")
    Optional<LabTestMainTableForMainTestName> findByTestName(@Param("testName") String testName);


    @Query("SELECT COUNT(l) > 0 FROM LabTestMainTableForMainTestName l WHERE :name IS NULL OR LOWER(l.testName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Boolean existsAllByLabTestName(@Param("name") String name);

    @Query("SELECT ltmt FROM LabTestMainTableForMainTestName ltmt WHERE ltmt.price > 0.0")
    Page<LabTestMainTableForMainTestName> getAllWithPrice(Pageable pageable);
}
