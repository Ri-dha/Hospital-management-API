package com.azu.hospital.consultant.consultantPatient.dao;

import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;

import java.util.List;
import java.util.Optional;

public interface ConsultantPatientDao {

    Optional<ConsultantPatient> findByPatientId(Long patientId);
    List<ConsultantPatient> getAllDoctorId(Long doctorId);
    ConsultantPatient createNewConsultantPatient(ConsultantPatient consultantPatient);

}
