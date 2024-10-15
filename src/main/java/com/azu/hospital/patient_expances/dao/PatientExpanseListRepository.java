package com.azu.hospital.patient_expances.dao;


import com.azu.hospital.accounting.add_priceses.dto.BillsDtoSum;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface PatientExpanseListRepository extends JpaRepository<PatientExpanseList, Long> {

    @Query("SELECT l FROM PatientExpanseList l WHERE l.patient.id = :patientId")
    List<PatientExpanseList> getAllByPatientId(@Param("patientId") Long patientId);

    @Query("SELECT p FROM PatientExpanseList p WHERE p.patient.patientData.fullName " +
            "LIKE  concat('%' , lower(:patientName) , '%')" +
            "ORDER BY p.updateAt DESC , p.createdAt DESC ")
    Page<PatientExpanseList> findAllWithFilter(String patientName, Pageable pageable);


}
