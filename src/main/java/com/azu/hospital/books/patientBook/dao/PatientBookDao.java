package com.azu.hospital.books.patientBook.dao;

import com.azu.hospital.books.patientBook.PatientBookType;
import com.azu.hospital.books.patientBook.entity.PatientBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientBookDao {

    void createPatientBookBody(PatientBook patientBook);

    void updatePatientBook(PatientBook patientBook);

    void deletePatientBook(Long id);

    Optional<PatientBook> getPatientBookById(Long id);

    Page<PatientBook> getAllPatientBookByTypeAndByPatientId(PatientBookType type, Long patientId, Pageable pageable);

    Page<PatientBook> getAllPatientBook(Pageable pageable);






}
