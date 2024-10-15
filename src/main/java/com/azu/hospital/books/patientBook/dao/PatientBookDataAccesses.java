package com.azu.hospital.books.patientBook.dao;


import com.azu.hospital.books.patientBook.PatientBookType;
import com.azu.hospital.books.patientBook.entity.PatientBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("PatientBookDataRepository")
public class PatientBookDataAccesses implements PatientBookDao {

    private final PatientBookRepository repository;

    @Autowired
    public PatientBookDataAccesses(@Qualifier("patientBookRepository") PatientBookRepository repository) {
        this.repository = repository;

    }


    @Override
    public void createPatientBookBody(PatientBook patientBook) {
        repository.save(patientBook);
    }

    @Override
    public void updatePatientBook(PatientBook patientBook) {
        repository.save(patientBook);
    }

    @Override
    public void deletePatientBook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PatientBook> getPatientBookById(Long id) {
        return  repository.findById(id);
    }

    @Override
    public Page<PatientBook> getAllPatientBookByTypeAndByPatientId(PatientBookType type, Long patientId, Pageable pageable) {
        return null;
    }


    @Override
    public Page<PatientBook> getAllPatientBook(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
