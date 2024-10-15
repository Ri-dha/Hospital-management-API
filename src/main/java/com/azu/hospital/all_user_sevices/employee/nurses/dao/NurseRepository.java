package com.azu.hospital.all_user_sevices.employee.nurses.dao;

import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
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
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findNurseById(Long nurseId);

    Optional<Nurse> findByUsername(String username);

    Optional<Nurse> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Nurse> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    @Query("SELECT u FROM Nurse u " +
            "WHERE (LOWER(u.username) LIKE LOWER(CONCAT('%', COALESCE(:username, ''), '%')) OR :username IS NULL) " +
            "AND (u.email = :email OR :email IS NULL) " +
            "AND (LOWER(u.specialist) = LOWER(:specialist) OR :specialist IS NULL) " +
            "AND (u.bloodGroup LIKE :bloodGroup OR :bloodGroup IS NULL) " +
            "AND (u.mobile = :mobile OR :mobile IS NULL) " +
            "AND (LOWER(u.gender) = LOWER(:gender) OR :gender IS NULL)")
    Page<Nurse> findNursesBy(
            @Param("username") String username,
            @Param("email") String email,
            @Param("specialist") String specialist,
            @Param("bloodGroup") String bloodGroup,
            @Param("mobile") String mobile,
            @Param("gender") String gender,
            Pageable pageable
    );
}
