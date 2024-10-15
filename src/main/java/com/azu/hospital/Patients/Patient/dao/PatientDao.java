package com.azu.hospital.Patients.Patient.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.request.GetAllPatientRequest;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.patient.BillState;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@Transactional
public interface PatientDao {

    Optional<Patient> getPatientById(Long id);

    Page<Patient> getAllPatient(Pageable pageable);

    Patient insertPatient(Patient patient);

    Patient updatePatient(Patient patient);

    void deletePatientById(Long id);

    boolean existsPatientById(Long id);

    Page<Patient> getPatientByWardIdWithOnlySearch(Long id, String patientName, Pageable pageable);

    Page<Patient> getPatientArchiveByWardIdWithOnlySearch(Long id, String patientName, Pageable pageable);

    Page<Patient> getPatientByWardIdWithOnlySearchAndFilter(Long id, String patientName, Long doctorId,
                                                            Pageable pageable);

    Page<Patient> getPatientArchiveByWardIdWithOnlySearchAndFilter(Long id, String patientName, Long doctorId,
                                                                   Pageable pageable);

    Page<Patient> getAllPatientFilter(GetAllPatientRequest request, BillState state, Pageable pageable);

    Page<Patient> getAllPatientArchiveFilter(GetAllPatientRequest request, Pageable pageable);

    Page<Patient> getAllPatientByDoctorIdFilter(Long doctor, String name, Integer first_age, Integer last_age,
                                                String admissionDate,
                                                Pageable pageable);

    Page<Patient> getAllPatientArchiveByDoctorIdFilter(Long doctor, String name, Integer first_age, Integer last_age,
                                                       String admissionDate,
                                                       Pageable pageable);


    Page<Patient> getAllArchivedPatient(Pageable pageable);

    Page<Patient> getAllPatientWithFilter(String patientName, String doctorName, String wardName, Pageable pageable);

    Boolean existsByFullName(String fullName);

    Boolean existsByMobile(String mobile);

    Long countAll();

    Page<Patient> findAllPatientsByWardId(Long wardId, Pageable pageable);

    Page<Patient> getAllPatientWithFilter(GetAllPatientRequest request, BillState state, Pageable pageable);

}
