package com.azu.hospital.consultant.consultantPatient.dao;

import com.azu.hospital.consultant.consultantPatient.entity.ConsultantPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("consultantPatientJpa")
public class ConsultantPatientJpa implements ConsultantPatientDao {

    private final ConsultantPatientRepository consultantPatientRepository;

    @Autowired
    public ConsultantPatientJpa(ConsultantPatientRepository consultantPatientRepository) {
        this.consultantPatientRepository = consultantPatientRepository;
    }

    @Override
    public Optional<ConsultantPatient> findByPatientId(Long patientId) {
        return consultantPatientRepository.findById(patientId);
    }

    @Override
    public List<ConsultantPatient> getAllDoctorId(Long doctorId) {
        return consultantPatientRepository.getAllByDoctorIdOrderByCreatedAtDesc(doctorId);
    }

    @Override
    public ConsultantPatient createNewConsultantPatient(ConsultantPatient consultantPatient) {
        return consultantPatientRepository.save(consultantPatient);
    }
}
