package com.azu.hospital.patient_expances.dao;

import com.azu.hospital.patient_expances.entity.PatientExpanse;

import java.util.List;
import java.util.Optional;

public interface PatientExpanseDao {


    void addPatientExpanseList(List<PatientExpanse> request);

    void updatePatientExpanse(PatientExpanse update);

    Optional<PatientExpanse> getPatientExpanseById(Long id);


    List<PatientExpanse> findAllReceivedPatientExpanseRequestByPatientId(Long patientId);



}
