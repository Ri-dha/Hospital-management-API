package com.azu.hospital.all_user_sevices.roles_sevices.dao;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.roles_sevices.RoleRepository;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("RoleJpa")
public class RoleJpaDataAccess implements RoleDao{

    private final RoleRepository repository;

    public RoleJpaDataAccess(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role addNewRole(Role role) {
        return repository.save(role);
    }

    @Override
    public void updateExitsRole(Role update) {
        repository.save(update);
    }

    @Override
    public void deleteExistsRole(Integer roleId) {
        repository.deleteById(roleId);
    }

    @Override
    public List<Role> getAllRole() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Integer roleId) {
        return repository.findById(roleId);
    }

    @Override
    public Boolean isRoleNameExist(String roleName) {
        return repository.existsByName(roleName);
    }

    @Override
    public Set<Role> findRoleByRoleName(Set<String> roleName) {
        return repository.findByNames(roleName);
    }
}
