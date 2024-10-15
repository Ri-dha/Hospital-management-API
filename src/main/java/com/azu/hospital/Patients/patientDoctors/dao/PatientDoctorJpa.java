package com.azu.hospital.Patients.patientDoctors.dao;

import com.azu.hospital.Patients.patientDoctors.entity.PatientDoctor;
import com.azu.hospital.utils.enums.patient.PatientDoctorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("patientDoctorJpa")
public class PatientDoctorJpa implements PatientDoctorDao{

    private final PatientDoctorRepository patientDoctorRepository;

    @Autowired
    public PatientDoctorJpa(PatientDoctorRepository patientDoctorRepository) {
        this.patientDoctorRepository = patientDoctorRepository;
    }


    @Override
    public PatientDoctor getMainDoctor(Long patientId) {
        return patientDoctorRepository.findByPatientIdAndState(patientId, PatientDoctorState.Main);
    }

    @Override
    public Optional<PatientDoctor> findById(Long patientDoctorId) {
        return patientDoctorRepository.findById(patientDoctorId);
    }

    @Override
    public PatientDoctor newPatientDoctor(PatientDoctor patientDoctor) {
        return patientDoctorRepository.save(patientDoctor);
    }

    @Override
    public List<PatientDoctor> getAllPatientId(Long patientId) {
        return patientDoctorRepository.getAllByPatientIdOrderByStateDesc(patientId);
    }

    @Override
    public Optional<PatientDoctor> findByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return patientDoctorRepository.findByPatientIdAndDoctorId(patientId,doctorId);
    }

    @Override
    public Boolean existsByPatientIdAndDoctorId(Long patientId, Long doctorId) {
        return patientDoctorRepository.existsByPatientIdAndDoctorId(patientId,doctorId);
    }

    @Override
    public void updatePatientDoctor(PatientDoctor patientDoctor) {
        patientDoctorRepository.save(patientDoctor);
    }

    @Override
    public void updateAllPatientDoctor(List<PatientDoctor> patientDoctors) {
        patientDoctorRepository.saveAll(patientDoctors);
    }

    @Override
    public void deleteById(Long patientDoctorId) {
        patientDoctorRepository.deleteById(patientDoctorId);
    }
}
