package com.azu.hospital.lab_collection.test_list.dao;

import com.azu.hospital.lab_collection.test_list.entity.LabList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LabListDao {

    Page<LabList> getAllLabList(String testName , Pageable pageable);
    LabList createNewLabList(LabList labList);

    List<LabList> createManyLabList(List<LabList> labLists);

    Optional<LabList> findLabListById(Long id);

    void updateLabList(LabList labList);

    void deleteLabListById(Long id);
}
