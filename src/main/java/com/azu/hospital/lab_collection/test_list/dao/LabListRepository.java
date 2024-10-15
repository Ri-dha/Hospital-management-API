package com.azu.hospital.lab_collection.test_list.dao;

import com.azu.hospital.lab_collection.test_list.entity.LabList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface LabListRepository extends JpaRepository<LabList , Long> {

    @Query("SELECT l FROM LabList l WHERE lower(l.labName) LIKE lower(concat('%', :testName, '%'))")
    Page<LabList> findAllByTestName(String testName, Pageable pageable);

}
