package com.azu.hospital.all_user_sevices.user_utils.base_user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository("BaseUserJpa")
public class BaseUserJpaDataAccess implements BaseUserDao {

    private final BaseUserRepository repository;

    @Autowired
    public BaseUserJpaDataAccess(BaseUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BaseUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<BaseUser> findById(Long id) {
        return repository.findBaseUserById(id);
    }

    @Override
    public Page<BaseUser> getAllByRaise(Instant date, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BaseUser> getAllByBonus(Instant date, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BaseUser> getAllByPayCut(Instant date, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BaseUser> getAllByRating(Instant date, Pageable pageable) {
        return repository.getAllByRating(date, pageable);
    }

    @Override
    public Page<BaseUser> getAllByWardOrUnit(Long id, Pageable pageable) {
//        return repository.getAllByWardOrUnit(id , pageable);

        return null;
    }

    @Override
    public void deleteBaseUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateBaseUser(BaseUser user) {
        repository.save(user);
    }


    @Override
    public Optional<BaseUser> findByToken(String token) {
        return repository.findAllByTokens(token);
    }

    @Override
    public Page<BaseUser> getAllDoctorsByRole(List<Integer> roleIds, Pageable pageable) {
        return repository.getAllUsersByRoleIds(roleIds, pageable);
    }

    @Override
    public Long userCount() {
        return repository.count();
    }

    @Override
    public List<BaseUser> findByRole(String role) {
        return repository.findByRolesContains(role);
    }

    @Override
    public List<BaseUser> getUsersByRole(List<String> role) {
        return repository.getUsersByRole(role);
    }

    @Override
    public List<BaseUser> findAllUsersBy(String search, String roleName, Pageable pageable) {
        return repository.findUsersBy(search, roleName, pageable);
    }

    @Override
    public Long countAllItems(String search, String roleName) {
        return repository.countUsersBy(search, roleName);
    }

    @Override
    public Long countAllUsersBy(String search, String roleName) {
        return repository.countAllUsersBy(search, roleName);
    }

    @Override
    public List<BaseUser> getAllUsersByIdIn(List<Long> ids) {
        return repository.getAllUsersById(ids);
    }

    @Override
    public Long countAll() {
        return repository.count();
    }




}
