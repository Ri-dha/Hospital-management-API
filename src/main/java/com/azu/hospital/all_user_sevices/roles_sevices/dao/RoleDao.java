package com.azu.hospital.all_user_sevices.roles_sevices.dao;

import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.utils.enums.EnumRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleDao {

    Role addNewRole(Role role);

    void updateExitsRole(Role update);

    void deleteExistsRole(Integer roleId);

    List<Role> getAllRole();

    Optional<Role> findRoleById(Integer roleId);

    Boolean isRoleNameExist(String roleName);

    Set<Role> findRoleByRoleName(Set<String> roleName);
}
