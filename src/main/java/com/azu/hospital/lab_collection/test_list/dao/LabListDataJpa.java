package com.azu.hospital.lab_collection.test_list.dao;

import com.azu.hospital.lab_collection.test_list.entity.LabList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("labListRepo")
public class LabListDataJpa implements LabListDao{

    private final LabListRepository labListRepository;

    @Autowired
    public LabListDataJpa(LabListRepository labListRepository) {
        this.labListRepository = labListRepository;
    }


    @Override
    public Page<LabList> getAllLabList(String testName, Pageable pageable) {
        return labListRepository.findAllByTestName(testName,pageable);
    }

    @Override
    public LabList createNewLabList(LabList labList) {
        return labListRepository.save(labList);
    }

    @Override
    public List<LabList> createManyLabList(List<LabList> labLists) {
        return labListRepository.saveAll(labLists);
    }

    @Override
    public Optional<LabList> findLabListById(Long id) {
        return labListRepository.findById(id);
    }

    @Override
    public void updateLabList(LabList labList) {
        labListRepository.save(labList);
    }

    @Override
    public void deleteLabListById(Long id) {
      labListRepository.deleteById(id);
    }
}
