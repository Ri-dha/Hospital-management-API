package com.azu.hospital.all_user_sevices.user_dao;

import java.util.List;
import java.util.Optional;


import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



import com.azu.hospital.all_user_sevices.user_entity.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    @Query("SELECT u FROM User u " +
            "WHERE (LOWER(u.username) = LOWER(:username) OR :username IS NULL) " +
            "AND (u.email = :email OR :email IS NULL) " +
            "AND (LOWER(u.specialist) = LOWER(:specialist) OR :specialist IS NULL) " +
            "AND (u.bloodGroup LIKE :bloodGroup OR :bloodGroup IS NULL) " +
            "AND (u.mobile = :mobile OR :mobile IS NULL) " +
            "AND (LOWER(u.gender) = LOWER(:gender) OR :gender IS NULL)")
    List<User> findUsersBy(
            @Param("username") String username,
            @Param("email") String email,
            @Param("specialist") String specialist,
            @Param("bloodGroup") String bloodGroup,
            @Param("mobile") String mobile,
            @Param("gender") String gender
    );



    @Query("SELECT u FROM User u WHERE u.id = :userId AND :role MEMBER OF u.roles")
    Optional<User> findUsersByUserIdAndRoles(@Param("userId") Long userId, @Param("role") EnumRole role);


}
