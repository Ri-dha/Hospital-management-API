package com.azu.hospital.all_user_sevices.employee.nurses.dao;


import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NurseDao {


    Nurse createNewNurse(Nurse request);

    Page<Nurse> getAllNurses(Pageable pageable);

    void updateCurrentNurse(Nurse update);

    void deleteExistNurse(Long nurseId);


    Optional<Nurse> findNurseById(Long id);

    Optional<Nurse> findByUsername(String username);

    Optional<Nurse> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Nurse> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    Page<Nurse> findAllNursesBy(
            String username,
            String email,
            String specialist,
            String bloodGroup,
            String mobile,
            String gender,
            Pageable pageable
    );



    Optional<Nurse> getNurseByToken(String token);

    Long countAllItems();




}
