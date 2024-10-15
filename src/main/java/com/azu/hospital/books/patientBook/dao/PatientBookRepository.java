package com.azu.hospital.books.patientBook.dao;

import com.azu.hospital.books.patientBook.PatientBookType;
import com.azu.hospital.books.patientBook.entity.PatientBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientBookRepository extends JpaRepository<PatientBook, Long> {

    @Query("select p from PatientBook p where " +
            "(:type is null or p.patientBookType = :type) and " +
            "(:patientId is null or p.patient.id = :patientId)")
    Page<PatientBook> getAllPatientBookByTypeAndByPatientId(PatientBookType type, Long patientId, Pageable pageable);
}
