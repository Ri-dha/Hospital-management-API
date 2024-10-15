package com.azu.hospital.all_user_sevices.roles_sevices;

import com.azu.hospital.all_user_sevices.roles_sevices.dao.RoleDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.utils.enums.EnumRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(
            @Qualifier("RoleJpa") RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role createNewRole(Role request){
        Boolean roleAlreadyExists = roleDao.isRoleNameExist(request.getName());
        if(roleAlreadyExists){
            throw new DuplicateResourceException(
                 "Role already exists"
            );
        }
       return roleDao.addNewRole(request);
    }



    public void updateRole(Integer roleId, Role update){
        Optional<Role> role = Optional.ofNullable(roleDao.findRoleById(roleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Role Not Found"
                        )
                ));
        Role newRole = role.get();
        if (update.getName() != null){
            newRole.setName(update.getName());
        }
        roleDao.updateExitsRole(newRole);
    }

    public List<Role> getAllRoles(){
        return roleDao.getAllRole()
                .stream().toList();
    }

    public void deleteRoleById(Integer roleId){
        Optional<Role> role = Optional.ofNullable(roleDao.findRoleById(roleId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Role Not Found"
                        )
                ));
        roleDao.deleteExistsRole(roleId);
    }


    @Transactional()
    public Set<Role> findRolesByEnumSet(Set<EnumRole> enumRoles) {

        Set<String> roleNames = enumRoles.stream()
                .map(EnumRole::name)
                .collect(Collectors.toSet());

        return roleDao.findRoleByRoleName(roleNames);
    }
}
