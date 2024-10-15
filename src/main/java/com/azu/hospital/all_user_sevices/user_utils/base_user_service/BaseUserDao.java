package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface BaseUserDao {

    Optional<BaseUser> findByEmail(String email);

    Optional<BaseUser> findById(Long id);

    Page<BaseUser> getAllByRaise(Instant date, Pageable pageable);

    Page<BaseUser> getAllByBonus(Instant date, Pageable pageable);

    Page<BaseUser> getAllByPayCut(Instant date, Pageable pageable);

    Page<BaseUser> getAllByRating(Instant date, Pageable pageable);

    Page<BaseUser> getAllByWardOrUnit(Long id, Pageable pageable);


    void deleteBaseUserById(Long id);

    void updateBaseUser(BaseUser user);

    Optional<BaseUser> findByToken(String token);


    Page<BaseUser> getAllDoctorsByRole(List<Integer> roleIds, Pageable pageable);

    Long userCount();

    List<BaseUser> findByRole(String role);

    List<BaseUser> getUsersByRole(List<String> role);

    List<BaseUser> findAllUsersBy(
            String search,
            String roleName,
            Pageable pageable
    );

    Long countAllItems(String search, String roleName);

    Long countAllUsersBy(String search, String roleName);
    List<BaseUser> getAllUsersByIdIn(List<Long> ids);

    Long countAll();



}

