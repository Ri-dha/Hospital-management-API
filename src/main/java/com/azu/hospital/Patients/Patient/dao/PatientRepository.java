package com.azu.hospital.Patients.Patient.dao;

import com.azu.hospital.Patients.Patient.Entity.Patient;
import com.azu.hospital.Patients.Patient.request.GetAllPatientRequest;
import com.azu.hospital.Patients.Patient.request.PatientData;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.patient.BillState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p LEFT JOIN p.doctor pd WHERE " +
            "LOWER(p.patientData.fullName) LIKE LOWER(CONCAT('%', :#{#request.patientName} , '%')) " +
            "AND p.patientDate.age BETWEEN :#{#request.minAge} AND :#{#request.maxAge} " +
            "AND LOWER(p.patientDate.admissionDate) LIKE LOWER(CONCAT('%', :#{#request.admissionDate}, '%')) " +
            "AND (:#{#request.wardId} IS NULL OR p.ward.wardId = :#{#request.wardId}) " +
            "AND (:#{#request.doctorName} IS NULL OR LOWER(pd.doctor.username) LIKE LOWER(CONCAT('%', :#{#request.doctorName}, '%'))) " +
            "and (:state IS NULL OR p.billState = :state)" +
            "and p.isArchived = false"
    )
    Page<Patient> getAllPatient(
            @Param("request") GetAllPatientRequest request,
            @Param("state") BillState state,
            Pageable pageable);

    @Query("SELECT p FROM Patient p LEFT JOIN p.doctor pd WHERE " +
            "LOWER(p.patientData.fullName) LIKE LOWER(CONCAT('%', :#{#request.patientName} , '%')) " +
            "AND p.patientDate.age BETWEEN :#{#request.minAge} AND :#{#request.maxAge} " +
            "AND LOWER(p.patientDate.admissionDate) LIKE LOWER(CONCAT('%', :#{#request.admissionDate}, '%')) " +
            "AND (:#{#request.wardId} IS NULL OR p.ward.wardId = :#{#request.wardId}) " +
            "AND (:#{#request.doctorName} IS NULL OR LOWER(pd.doctor.username) LIKE LOWER(CONCAT('%', :#{#request.doctorName}, '%'))) " +
            "and (:state IS NULL OR p.billState = :state)"
    )
    Page<Patient> getAllPatientWithFilter(
            @Param("request") GetAllPatientRequest request,
            @Param("state") BillState state,
            Pageable pageable);

    @Query("SELECT p FROM Patient p LEFT JOIN p.doctor pd WHERE " +
            "LOWER(p.patientData.fullName) LIKE LOWER(CONCAT('%', :#{#request.patientName} , '%')) " +
            "AND p.patientDate.age BETWEEN :#{#request.minAge} AND :#{#request.maxAge} " +
            "AND LOWER(p.patientDate.admissionDate) LIKE LOWER(CONCAT('%', :#{#request.admissionDate}, '%')) " +
            "AND (:#{#request.wardId} IS NULL OR p.ward.wardId = :#{#request.wardId}) " +
            "AND (:#{#request.doctorName} IS NULL OR LOWER(pd.doctor.username) LIKE LOWER(CONCAT('%', :#{#request.doctorName}, '%'))) " +
            "AND p.isArchived = true"
    )
    Page<Patient> getAllArchivedPatient(
            @Param("request") GetAllPatientRequest request,
            Pageable pageable);




    Page<Patient> getAllByDoctorDoctorIdAndPatientDataFullNameContainingIgnoreCaseAndPatientDateAgeBetweenAndPatientDateAdmissionDateContainingIgnoreCaseAndIsArchived(
            Long doctor,
            String patientData_fullName,
            Integer first_age,
            Integer last_age,
            String date,
            Boolean isArchived,
            Pageable pageable
    );

    @Query("SELECT p FROM Patient p " +
            "JOIN p.ward w " +
            "JOIN p.patientData pd " +
            "WHERE w.wardId = :wardId " +
            "AND LOWER(pd.fullName) LIKE LOWER(CONCAT('%', COALESCE(:patientName, ''), '%')) " +
            "AND p.isArchived = :isArchived")
    Page<Patient> getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndIsArchived(
            @Param("wardId") Long wardId,
            @Param("patientName") String patientName,
            @Param("isArchived") Boolean isArchived,
            Pageable pageable
    );


    @Query("SELECT p FROM Patient p " +
            "JOIN p.ward w " +
            "JOIN p.patientData pd " +
            "JOIN p.doctor d " +
            "WHERE w.wardId = :wardId " +
            "AND LOWER(pd.fullName) LIKE LOWER(CONCAT('%', COALESCE(:patientName, ''), '%')) " +
            "AND d.doctor.id = :doctorId " +
            "AND p.isArchived = :isArchived")
    Page<Patient> getAllByWardWardIdAndPatientDataFullNameContainingIgnoreCaseAndDoctorDoctorIdIsAndIsArchived(
            @Param("wardId") Long wardId,
            @Param("patientName") String patientName,
            @Param("doctorId") Long doctorId,
            @Param("isArchived") Boolean isArchived,
            Pageable pageable
    );



    @Query("SELECT p FROM Patient p " +
            "WHERE p.ward.wardId = :wardId AND p.isArchived = false")
    Page<Patient> getAllPatientsByWardId(@Param("wardId") Long wardId, Pageable pageable);


    Page<Patient> getAllByIsArchived(Boolean isArchived,Pageable pageable);

    @Query("SELECT p FROM Patient p " +
            "LEFT JOIN p.doctor pd " +
            "LEFT JOIN pd.doctor d " +
            "LEFT JOIN p.ward w " +
            "WHERE (LOWER(p.patientData.fullName) LIKE LOWER(CONCAT('%', COALESCE(:patientName, ''), '%')) OR :patientName IS NULL) AND " +
            "(LOWER(d.username) LIKE LOWER(CONCAT('%', COALESCE(:doctorName, ''), '%')) OR :doctorName IS NULL) AND " +
            "(LOWER(w.name) LIKE LOWER(CONCAT('%', COALESCE(:wardName, ''), '%')) OR :wardName IS NULL)")
    Page<Patient> getAllPatientWithFilter(
            @Param("patientName") String patientName,
            @Param("doctorName") String doctorName,
            @Param("wardName") String wardName,
            Pageable pageable
    );

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Patient p " +
            "WHERE LOWER(p.patientData.fullName) = LOWER(:fullName)")
    Boolean existsByPatientName(
            @Param("fullName") String fullName
    );

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Patient p " +
            "WHERE LOWER(p.patientContact.mobile) = LOWER(:mobile)")
    Boolean existsByMobile(String mobile);

}
