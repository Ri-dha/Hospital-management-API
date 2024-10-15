package com.azu.hospital.all_user_sevices.employee.doctors.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findDoctorById(Long doctorId);

    Optional<Doctor> findByUsername(String username);

    Optional<Doctor> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Doctor> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    @Query("SELECT u FROM Doctor u " +
            "WHERE (LOWER(u.username) LIKE CONCAT('%', LOWER(:username),'%' ) OR :username IS NULL) " +
            "AND (u.email = :email OR :email IS NULL) " +
            "AND (LOWER(u.specialist) = LOWER(:specialist) OR :specialist IS NULL) " +
            "AND (LOWER(u.subSpecialist) = LOWER(:subSpecialist) OR :subSpecialist IS NULL) " +
            "AND (u.bloodGroup LIKE :bloodGroup OR :bloodGroup IS NULL) " +
            "AND (u.mobile = :mobile OR :mobile IS NULL) " +
            "AND (LOWER(u.gender) = LOWER(:gender) OR :gender IS NULL)" +
            "order by coalesce(u.employeeDate , null)  ")
    Page<Doctor> findDoctorsBy(
            @Param("username") String username,
            @Param("email") String email,
            @Param("specialist") String specialist,
            @Param("subSpecialist") String subSpecialist,
            @Param("bloodGroup") String bloodGroup,
            @Param("mobile") String mobile,
            @Param("gender") String gender,
            Pageable pageable
    );



    @Query("SELECT u FROM Doctor u WHERE u.id = :doctorId AND :role MEMBER OF u.roles")
    Optional<Doctor> findDoctorsByDoctorIdAndRoles(@Param("doctorId") Long doctorId, @Param("role") EnumRole role);


    @Query("SELECT d FROM Doctor d WHERE d.id IN :doctorIds")
    List<Doctor> getDoctorByIdList(@Param("doctorIds") List<Long> doctorIds);


}
