package com.azu.hospital.Patients.surgicalList.dao;

import com.azu.hospital.Patients.surgicalList.entity.SurgicalList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository("surgicalListJpa")
public class SurgicalListDataJpa implements SurgicalListDao{

    private final SurgicalListRepository repository;

    @Autowired
    public SurgicalListDataJpa(
            SurgicalListRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<SurgicalList> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public SurgicalList createSurgicalList(SurgicalList surgicalList) {
        return repository.save(surgicalList);
    }

    @Override
    public Page<SurgicalList> getAllSurgicalList(Instant surgicalDate , Pageable pageable) {
        return repository.getAllBySurgicalDateOrderByState(surgicalDate , pageable);
    }

    @Override
    public void updateSurgicalList(SurgicalList surgicalList) {
        repository.save(surgicalList);
    }
}
