package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.EnumRole;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Transactional
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {

    @Query("SELECT bu FROM BaseUser bu WHERE bu.email = :email")
    Optional<BaseUser> findByEmail(@Param("email") String email);

    @Query("SELECT b.users FROM Token b WHERE b.token = :token")
    Optional<BaseUser> findAllByTokens(@Param("token") String token);

    Optional<BaseUser> findBaseUserById(@Param("id") Long id);

    @Query("SELECT u FROM BaseUser u JOIN u.roles r WHERE r.roleId IN :roleIds")
    Page<BaseUser> getAllUsersByRoleIds(@Param("roleIds") List<Integer> roleIds, Pageable pageable);

//    @Query("SELECT u FROM BaseUser u " +
//            "WHERE EXISTS (SELECT r from  Raise r " +
//            "WHERE EXTRACT(YEAR FROM r.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP))" +
//            "AND EXTRACT(MONTH FROM r.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP))" +
//            "AND u.id = r.user.id)")
//    Page<BaseUser> getAllByRaise(@Param("date") Instant date, Pageable pageable);

//    @Query("SELECT u FROM BaseUser u " +
//            "WHERE EXISTS (SELECT b from  Bonus b " +
//            "WHERE EXTRACT(YEAR FROM b.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP))" +
//            "AND EXTRACT(MONTH FROM b.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP))" +
//            "AND u.id = b.user.id)")
//    Page<BaseUser> getAllByBonus(@Param("date") Instant date, Pageable pageable);

//    @Query("SELECT u FROM BaseUser u " +
//            "WHERE EXISTS (SELECT p from  PayCat p " +
//            "WHERE EXTRACT(YEAR FROM p.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP))" +
//            "AND EXTRACT(MONTH FROM p.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP))" +
//            "AND u.id = p.user.id)")
//    Page<BaseUser> getAllByPayCut(@Param("date") Instant date, Pageable pageable);

    @Query("SELECT u FROM BaseUser u " +
            "ORDER BY (CASE WHEN EXISTS (SELECT r FROM UserRating r " +
            "WHERE EXTRACT(YEAR FROM r.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP)) " +
            "AND EXTRACT(MONTH FROM r.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP))" +
            "AND u.id = r.user.id ) THEN 1 ELSE 0 END) ASC ")
    Page<BaseUser> getAllByRating(@Param("date") Instant date, Pageable pageable);


//    @Query("select u from  BaseUser u where" +
//            " EXISTS (select us from UserShift us where us.user.id = u.id and (us.ward.wardId = " +
//            ":id or us.unit.unitId = :id))")
//    Page<BaseUser> getAllByWardOrUnit(Long id , Pageable pageable);


    @Query("SELECT u FROM BaseUser u JOIN u.roles r WHERE r.name IN :roles")
    List<BaseUser> getUsersByRole(List<String> roles);


    @Query("SELECT u FROM BaseUser u JOIN u.roles r WHERE r.name = :role")
    List<BaseUser> findByRolesContains(@Param("role") String role);


    @Query("SELECT b FROM BaseUser b JOIN b.roles r " +
            "WHERE ((LOWER(b.username) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%')) OR LOWER(b.email) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%'))) " +
            "AND (r.name = :roleName OR :roleName IS NULL)) " +
            "ORDER BY b.updatedAt DESC, b.createdAt DESC")
    List<BaseUser> findUsersBy(
            @Param("search") String search,
            @Param("roleName") String roleName,
            Pageable pageable
    );

    @Query("SELECT COUNT(b) FROM BaseUser b JOIN b.roles r " +
            "WHERE ((LOWER(b.username) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%')) OR LOWER(b.email) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%'))) " +
            "AND (r.name = :roleName OR :roleName IS NULL))")
    long countAllUsersBy(
            @Param("search") String search,
            @Param("roleName") String roleName
    );

    @Query("SELECT u FROM BaseUser u WHERE u.id IN :ids")
    List<BaseUser> getAllUsersById(List<Long> ids);



    @Query("SELECT COUNT(b) FROM BaseUser b JOIN b.roles r " +
            "WHERE ((LOWER(b.username) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%')) OR :search IS NULL) " +
            "AND (LOWER(b.email) LIKE LOWER(CONCAT('%', COALESCE(:search, ''), '%')) OR :search IS NULL)) " +
            "AND (r.name = :roleName OR :roleName IS NULL)")
    Long countUsersBy(
            @Param("search") String search,
            @Param("roleName") String roleName
    );
}
