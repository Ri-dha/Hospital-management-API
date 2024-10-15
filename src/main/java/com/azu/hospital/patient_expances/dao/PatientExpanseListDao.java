package com.azu.hospital.patient_expances.dao;

import com.azu.hospital.patient_expances.entity.PatientExpanse;
import com.azu.hospital.patient_expances.entity.PatientExpanseList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientExpanseListDao {

    void addPatientExpanseList(PatientExpanseList request);

    Optional<PatientExpanseList> getPatientExpanseById(Long id);

    List<PatientExpanseList> getAllPatientExpanseListByPatientId(Long patientId);

    Page<PatientExpanseList> getAllPatientExpanseList(String patientName,Pageable pageable);

    void updateList(PatientExpanseList patientExpanseList);
}
