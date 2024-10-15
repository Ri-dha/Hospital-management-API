package com.azu.hospital.all_user_sevices.user_dao;


import com.azu.hospital.all_user_sevices.user_entity.User;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("UserJpa")
public class UserJPADataAccess implements UserDao {

    private final UserRepository userRepository;


    @Autowired
    public UserJPADataAccess(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createNewUser(User request) {
         userRepository.save(request);
    }

    @Override
    public void updateCurrentUser(User update) {
        userRepository.save(update);
    }


    @Override
    public void deleteExistUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findByUsernameContainingOrEmailContaining(String username, String email) {
        return userRepository.findByUsernameContainingOrEmailContaining(username, email);
    }


    @Override
    public Boolean existsByMobile(String mobile) {
        return userRepository.existsByMobile(mobile);
    }

    @Override
    public List<User> findAllUsersBy(
            String username, String email,
            String specialist,
            String bloodGroup, String mobile, String gender
    ) {
        return userRepository.findUsersBy(username, email, bloodGroup,bloodGroup, mobile, gender);
    }

    @Override
    public Optional<User> findUsersByUserIdAndRoles(Long id, EnumRole roles) {
        return userRepository.findUsersByUserIdAndRoles(id , roles);
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        return Optional.empty();
                //userRepository.findUserByTokens(token);
    }

    @Override
    public Long countAllItems() {
        return userRepository.count();
    }


}
