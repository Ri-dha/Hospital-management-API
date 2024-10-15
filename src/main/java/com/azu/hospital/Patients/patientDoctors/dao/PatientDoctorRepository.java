package com.azu.hospital.Patients.patientDoctors.dao;

import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Long> {


    Optional<PatientDoctor> findByPatientIdAndDoctorId(Long patientId, Long doctorId);

    Boolean existsByPatientIdAndDoctorId(Long patientId, Long doctorId);

    PatientDoctor findByPatientIdAndState(Long patientId, PatientDoctorState state);

    List<PatientDoctor> getAllByPatientIdOrderByStateDesc(Long patientId);

}
