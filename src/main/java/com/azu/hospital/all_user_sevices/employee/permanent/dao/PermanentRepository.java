package com.azu.hospital.all_user_sevices.employee.permanent.dao;

import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
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
public interface PermanentRepository extends JpaRepository<Permanent, Long> {

    Optional<Permanent> findPermanentById(Long permanentId);

    Optional<Permanent> findByUsername(String username);

    Optional<Permanent> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Permanent> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    @Query("SELECT u FROM Permanent u " +
            "WHERE (LOWER(u.username) = LOWER(:username) OR :username IS NULL) " +
            "AND (u.email = :email OR :email IS NULL) " +
            "AND (LOWER(u.specialist) = LOWER(:specialist) OR :specialist IS NULL) " +
            "AND (u.bloodGroup LIKE :bloodGroup OR :bloodGroup IS NULL) " +
            "AND (u.mobile = :mobile OR :mobile IS NULL) " +
            "AND (LOWER(u.gender) = LOWER(:gender) OR :gender IS NULL)")
    Page<Permanent> findPermanentsBy(
            @Param("username") String username,
            @Param("email") String email,
            @Param("specialist") String specialist,
            @Param("bloodGroup") String bloodGroup,
            @Param("mobile") String mobile,
            @Param("gender") String gender,
            Pageable pageable
    );



    @Query("SELECT u FROM Permanent u WHERE u.id = :permanentId AND :role MEMBER OF u.roles")
    Optional<Permanent> findPermanentsByPermanentIdAndRoles(@Param("permanentId") Long permanentId, @Param("role") EnumRole role);


}
