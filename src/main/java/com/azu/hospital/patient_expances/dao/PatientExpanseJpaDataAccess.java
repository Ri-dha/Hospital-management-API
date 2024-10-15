package com.azu.hospital.patient_expances.dao;

import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.patient_expances.entity.PatientExpanse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("PatientExpanseJpa")
public class PatientExpanseJpaDataAccess implements PatientExpanseDao{

    private final PatientExpanseRepository repository;

    @Autowired
    public PatientExpanseJpaDataAccess(PatientExpanseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addPatientExpanseList(List<PatientExpanse> request) {
        repository.saveAll(new ArrayList<>(request));
    }

    @Override
    public void updatePatientExpanse(PatientExpanse update) {
        repository.save(update);
    }

    @Override
    public Optional<PatientExpanse> getPatientExpanseById(Long id) {
        return repository.findById(id);
    }



    @Override
    public List<PatientExpanse> findAllReceivedPatientExpanseRequestByPatientId(Long patientId) {
        return repository.findAllReceivedDrugsRequestByPatientId(patientId);
    }




}
