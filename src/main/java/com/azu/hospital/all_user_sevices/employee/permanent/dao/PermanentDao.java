package com.azu.hospital.all_user_sevices.employee.permanent.dao;


import com.azu.hospital.all_user_sevices.employee.permanent.entity.Permanent;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PermanentDao {


    Permanent createNewPermanent(Permanent request);

    Page<Permanent> getAllPermanents(Pageable pageable);

    void updateCurrentPermanent(Permanent update);

    void deleteExistPermanent(Long permanentId);


    Optional<Permanent> findPermanentById(Long id);

    Optional<Permanent> findByUsername(String username);

    Optional<Permanent> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<Permanent> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    Page<Permanent> findAllPermanentsBy(
            String username,
            String email,
            String specialist,
            String bloodGroup,
            String mobile,
            String gender,
            Pageable pageable
    );


    Optional<Permanent> findPermanentsByPermanentIdAndRoles(Long id , EnumRole roles);


    Optional<Permanent> getPermanentByToken(String token);
    Long countAllItems();
}
