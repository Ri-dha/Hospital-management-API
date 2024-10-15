package com.azu.hospital.Patients.Patient.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.request.GetAllPatientRequest;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.patient.BillState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("patientRepo")
public class PatientJpaDataAccess implements PatientDao{

    private final PatientRepository patientRepository;

    public PatientJpaDataAccess(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Page<Patient> getAllPatient(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient insertPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Patient patient) {
     return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
      patientRepository.deleteById(id);
    }

    @Override
    public boolean existsPatientById(Long id) {
        return patientRepository.existsById(id);
    }

    @Override
    public Page<Patient> getPatientByWardIdWithOnlySearch(Long id, String patientName, Pageable pageable) {
        return patientRepository.getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndIsArchived(id ,
                patientName ,
                false,
                pageable);
    }

    @Override
    public Page<Patient> getPatientArchiveByWardIdWithOnlySearch(Long id, String patientName, Pageable pageable) {
        return patientRepository.getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndIsArchived(id ,
                patientName ,
                true,
                pageable);
    }

    @Override
    public Page<Patient> getPatientByWardIdWithOnlySearchAndFilter(Long id, String patientName, Long doctorId, Pageable pageable) {
        return patientRepository.getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndDoctorDoctorIdIsAndIsArchived(id,
                patientName,
                doctorId,
                false,
                pageable);
    }

    @Override
    public Page<Patient> getPatientArchiveByWardIdWithOnlySearchAndFilter(Long id, String patientName, Long doctorId, Pageable pageable) {
        return patientRepository.getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndDoctorDoctorIdIsAndIsArchived(id,
                patientName,
                doctorId,
                true,
                pageable);
    }


    @Override
    public Page<Patient> getAllPatientFilter(GetAllPatientRequest request, BillState state, Pageable pageable) {
        return patientRepository.getAllPatient(request,state,pageable);
    }

    @Override
    public Page<Patient> getAllPatientArchiveFilter(GetAllPatientRequest request, Pageable pageable) {
        return patientRepository.getAllArchivedPatient(request ,pageable);
    }


    @Override
    public Page<Patient> getAllPatientByDoctorIdFilter(Long doctor, String name, Integer first_age, Integer last_age,
                                                       String admissionDate, Pageable pageable) {
        return patientRepository.getAllByDoctorDoctorIdAndPatientDataFullNameContainingIgnoreCaseAndPatientDateAgeBetweenAndPatientDateAdmissionDateContainingIgnoreCaseAndIsArchived(doctor , name  ,
                first_age ,
                last_age,
                admissionDate,
                false,
                pageable);
    }

    @Override
    public Page<Patient> getAllPatientArchiveByDoctorIdFilter(Long doctor, String name, Integer first_age, Integer last_age, String admissionDate, Pageable pageable) {
        return patientRepository.getAllByDoctorDoctorIdAndPatientDataFullNameContainingIgnoreCaseAndPatientDateAgeBetweenAndPatientDateAdmissionDateContainingIgnoreCaseAndIsArchived(doctor , name  ,
                first_age ,
                last_age,
                admissionDate,
                true,
                pageable);
    }

    @Override
    public Page<Patient> getAllArchivedPatient(Pageable pageable) {
        return patientRepository.getAllByIsArchived(true,pageable);
    }

    @Override
    public Page<Patient> getAllPatientWithFilter(String patientName, String doctorName, String wardName, Pageable pageable) {
        return patientRepository.getAllPatientWithFilter(patientName,doctorName,wardName,pageable);
    }

    @Override
    public Boolean existsByFullName(String fullName) {
        return patientRepository.existsByPatientName(fullName);
    }

    @Override
    public Boolean existsByMobile(String mobile) {
        return patientRepository.existsByMobile(mobile);
    }

    @Override
    public Long countAll() {
        return patientRepository.count();
    }

    @Override
    public Page<Patient> findAllPatientsByWardId(Long wardId, Pageable pageable) {
        return patientRepository.getAllPatientsByWardId(wardId,pageable);
    }

    @Override
    public Page<Patient> getAllPatientWithFilter(GetAllPatientRequest request, BillState state, Pageable pageable) {
        return patientRepository.getAllPatientWithFilter(request,state,pageable);
    }


}
