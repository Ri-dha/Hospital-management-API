package com.azu.hospital.all_user_sevices.user_dao;


import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    void createNewUser(User request);

    void updateCurrentUser(User update);

    void deleteExistUser(Long userId);


    Optional<User> findUserById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByUsernameContainingOrEmailContaining(String username, String email);

    Boolean existsByMobile(String mobile);

    List<User> findAllUsersBy(
            String username,
            String email,
            String specialist,
            String bloodGroup,
            String mobile,
            String gender
    );


    Optional<User> findUsersByUserIdAndRoles(Long id , EnumRole roles);


    Optional<User> getUserByToken(String token);

    Long countAllItems();
}
