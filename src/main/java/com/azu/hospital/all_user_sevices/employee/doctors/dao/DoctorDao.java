package com.azu.hospital.all_user_sevices.employee.doctors.dao;


import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorDao {

    Doctor createNewDoctor(Doctor request);

    Page<Doctor> getAllDoctors(Pageable pageable);

    void updateCurrentDoctor(Doctor update);

    void deleteExistDoctor(Long doctorId);

    List<Doctor> getDoctorByIdList(List<Long> doctorIds);

    Optional<Doctor> findDoctorById(Long id);

    Optional<Doctor> findByUsername(String username);

    Optional<Doctor> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Doctor> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    Page<Doctor> findAllDoctorsBy(
            String username,
            String email,
            String specialist,
            String subSpecialist,
            String bloodGroup,
            String mobile,
            String gender,
            Pageable pageable
    );

    Page<Doctor> findAll(Pageable pageable);


    Optional<Doctor> findDoctorsByDoctorIdAndRoles(Long id , EnumRole roles);


    Optional<Doctor> getDoctorByToken(String token);




}
