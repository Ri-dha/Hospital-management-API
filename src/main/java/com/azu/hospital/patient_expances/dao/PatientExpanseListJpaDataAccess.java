package com.azu.hospital.patient_expances.dao;

import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PatientExpanseListJpa")
public class PatientExpanseListJpaDataAccess implements PatientExpanseListDao{

    private final PatientExpanseListRepository repository;

    @Autowired
    public PatientExpanseListJpaDataAccess(PatientExpanseListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addPatientExpanseList(PatientExpanseList request) {
        repository.save(request);
    }


    @Override
    public Optional<PatientExpanseList> getPatientExpanseById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PatientExpanseList> getAllPatientExpanseListByPatientId(Long patientId) {
        return repository.getAllByPatientId(patientId);
    }

    @Override
    public Page<PatientExpanseList> getAllPatientExpanseList(String patientName ,Pageable pageable) {
        return repository.findAllWithFilter(patientName,pageable);
    }

    @Override
    public void updateList(PatientExpanseList patientExpanseList) {
        repository.save(patientExpanseList);
    }
}
