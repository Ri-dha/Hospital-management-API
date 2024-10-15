package com.azu.hospital.Patients.patientDoctors.dao;

import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;

import java.util.List;
import java.util.Optional;

public interface PatientDoctorDao {

    PatientDoctor getMainDoctor(Long patientId);

    Optional<PatientDoctor> findById(Long patientDoctorId);
    PatientDoctor newPatientDoctor(PatientDoctor patientDoctor);

    List<PatientDoctor> getAllPatientId(Long patientId);
    Optional<PatientDoctor> findByPatientIdAndDoctorId(Long patientId , Long doctorId);
    Boolean existsByPatientIdAndDoctorId(Long patientId , Long doctorId);

    void updatePatientDoctor(PatientDoctor patientDoctor);
    void updateAllPatientDoctor(List<PatientDoctor> patientDoctors);

    void deleteById(Long patientDoctorId);

}
