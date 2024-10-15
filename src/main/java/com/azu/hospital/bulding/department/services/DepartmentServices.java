package com.azu.hospital.bulding.department.services;

import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.roles_sevices.dao.RoleDao;
import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDtoMapper;
import com.azu.hospital.bulding.department.dao.DepartmentDao;
import com.azu.hospital.bulding.department.dto.DepartmentDto;
import com.azu.hospital.bulding.department.dto.DepartmentDtoMapper;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.department.request.DepartmentBaseRequest;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.floor.dao.FloorDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.token.DeleteAllTokenServices;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.EnumDoctorRole;
import com.azu.hospital.utils.return_id.DtoForReturnIdOnly;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServices {

    private final DepartmentDao dao;
    private final DepartmentDtoMapper mapper;
    private final FloorDao floorDao;
    private final BaseUserDao baseUserDao;
    private final UserDao userDao;
    private final DoctorDao doctorDao;
    private final BaseUserDtoMapper userDtoMapper;
    private final ExceptionsMessageReturn messageReturn;

    private final RoleDao roleDao;

    private final DeleteAllTokenServices deleteAllTokenServices;


    public DepartmentServices(
            @Qualifier("depJpa") DepartmentDao dao,
            DepartmentDtoMapper mapper,
            @Qualifier("floorJpa") FloorDao floorDao,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            @Qualifier("UserJpa") UserDao userDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            BaseUserDtoMapper userDtoMapper, ExceptionsMessageReturn messageReturn, RoleDao roleDao, DeleteAllTokenServices deleteAllTokenServices) {
        this.dao = dao;
        this.mapper = mapper;
        this.floorDao = floorDao;
        this.baseUserDao = baseUserDao;
        this.userDao = userDao;
        this.doctorDao = doctorDao;
        this.userDtoMapper = userDtoMapper;
        this.messageReturn = messageReturn;
        this.roleDao = roleDao;
        this.deleteAllTokenServices = deleteAllTokenServices;
    }


    @Transactional
    public DtoForReturnIdOnly createNewDep(

            @RequestParam Long floorId,
            @ModelAttribute DepartmentBaseRequest request
    ) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Department department = new Department(
                request.getDepartmentName()
        );

        Floor floor = getFloorAndCheckUsers(floorId, request, messages);
        boolean newDepartment = dao.findDepartmentByDepName(request.getDepartmentName());
        if (newDepartment) {
            throw new DuplicateResourceException(
                    messages.getString("nameDuplicate") + request.getDepartmentName()
            );
        }
        if (request.getUserId() != null) {
            extractedDoctorManager(request, messages, department);
        }

        if (request.getUserAssistanceId() != null) {
            extractedUserAssistanceManger(request, messages, department);
        }

        department.setFloor(floor);

        floor.getDepartment().add(department);
        department = dao.createDepartment(department);
        return mapper.toDtoForDepartmentId(department);


    }

    private void extractedUserAssistanceManger(DepartmentBaseRequest request, ResourceBundle messages, Department department) {
        Doctor userAssistanceManager = doctorDao.findDoctorById(request.getUserAssistanceId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + request.getUserAssistanceId()
                        )
                );
        department.setDepartmentMangerAssistance(userAssistanceManager);

        Set<Role> roles = userAssistanceManager.getRoles();
        Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER_ASSISTANT.name()));
        roles.addAll(role);
        userAssistanceManager.setDepartmentMangerAssistance(department);
        deleteAllTokenServices.deleteAllTokens(userAssistanceManager.getId());
    }

    private void extractedDoctorManager(DepartmentBaseRequest request, ResourceBundle messages, Department department) {
        Doctor manager = doctorDao.findDoctorById(request.getUserId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("userNotFound") + request.getUserId()

                        )
                );
        department.setManager(manager);

        Set<Role> roles = manager.getRoles();
        Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER.name()));
        roles.addAll(role);

        manager.setDepartmentManger(department);
        deleteAllTokenServices.deleteAllTokens(manager.getId());
    }

    private Floor getFloorAndCheckUsers(Long floorId, DepartmentBaseRequest request, ResourceBundle messages) {
        if (Objects.equals(request.getUserId(), request.getUserAssistanceId()))
            throw new DuplicateResourceException(
                    messages.getString("userDuplicateId") + request.getUserId()
            );

        if (request.getUserId() != null) {
            if (dao.existsByManagerId(request.getUserId()) || dao.existsByAssistanceManagerId(request.getUserId())) {
                throw new DuplicateResourceException(
                        messages.getString("userJobDuplicate") + request.getUserId()
                );
            }
        }

        if (request.getUserAssistanceId() != null) {
            if (dao.existsByManagerId(request.getUserAssistanceId()) || dao.existsByAssistanceManagerId(request.getUserAssistanceId())) {
                throw new DuplicateResourceException(
                        messages.getString("userJobDuplicate") + request.getUserId()
                );
            }
        }


        return floorDao.selectFloorById(floorId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("floorNotFound") + floorId
                        )
                );
    }


    public List<DepartmentDto> getAllDepartments(Pageable pageable) {
        Page<Department> departmentPage = dao.getAllDepartment(pageable);
        return departmentPage.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(Long depId) {
        return dao.getById(depId).stream()
                .map(mapper)
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void updateDepartment(Long depId, Long floorId, DepartmentBaseRequest update) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Department department = dao.getById(depId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("departmentNotFound")
                        )
                );

        Doctor existManger = department.getManager();
        Doctor existAssistanceManager = department.getDepartmentMangerAssistance();

        if (existManger != null) {
            Set<Role> roles = existManger.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER.name()));
            roles.removeAll(role);
            existManger.setDepartmentManger(null);
            deleteAllTokenServices.deleteAllTokens(existManger.getId());
        }

        if (existAssistanceManager != null) {
            Set<Role> roles = existAssistanceManager.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER_ASSISTANT.name()));
            roles.removeAll(role);
            existAssistanceManager.setDepartmentManger(null);
            deleteAllTokenServices.deleteAllTokens(existAssistanceManager.getId());
        }


//        if (Objects.equals(update.getUserId(), update.getUserAssistanceId()))
//            throw new DuplicateResourceException(
//                    messages.getString("resourceFound")
//            );
//
//
//        if (update.getUserId() != null) {
//            if (dao.existsByManagerId(update.getUserId()) || dao.existsByAssistanceManagerId(update.getUserId())) {
//                throw new DuplicateResourceException(
//                        messages.getString("resourceFound")
//                );
//            }
//        }
//
//        if (update.getUserAssistanceId() != null) {
//            if (dao.existsByManagerId(update.getUserAssistanceId()) || dao.existsByAssistanceManagerId(update.getUserAssistanceId())) {
//                throw new DuplicateResourceException(
//                        messages.getString("resourceFound")
//                );
//            }
//
//        }

        boolean changed = false;

        if (update.getDepartmentName() != null) {
            department.setDepartmentName(update.getDepartmentName());
            changed = true;
        }
        if (update.getUserId() != null) {
            Doctor manager = doctorDao.findDoctorById(update.getUserId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("doctorNotFound")
                            )
                    );

            Set<Role> roles = manager.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER.name()));
            roles.addAll(role);
            department.setManager(manager);
            manager.setDepartmentManger(department);
            changed = true;
            deleteAllTokenServices.deleteAllTokens(manager.getId());
        }

        if (update.getUserAssistanceId() != null) {
            Doctor userAssistanceManager = doctorDao.findDoctorById(update.getUserAssistanceId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("doctorNotFound")
                            )
                    );
            Set<Role> roles = userAssistanceManager.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.DEPARTMENT_MANAGER_ASSISTANT.name()));
            roles.addAll(role);

            department.setDepartmentMangerAssistance(userAssistanceManager);
            userAssistanceManager.setDepartmentManger(department);
            changed = true;
            deleteAllTokenServices.deleteAllTokens(userAssistanceManager.getId());
        }

        if (floorId != null) {
            Floor floor = floorDao.selectFloorById(floorId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("floorNotFound")
                            )
                    );
            department.setFloor(floor);
            floor.setDepartment(List.of(department));
            changed = true;
        }
        if (!changed) {
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
        dao.updateDepartment(department);
    }

    public void deleteDepartmentById(@PathVariable Long depId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Department department = dao.getById(depId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        dao.deleteDepartment(depId);
    }


    public BaseUserDto getUserDepartment(Long userId) {
        return dao.getUserAsDepartmentManager(userId)
                .stream()
                .map(userDtoMapper)
                .findFirst()
                .orElse(null);
    }

    private BaseUserDto getUserDepartmentAssistantManager(Long userId) {
        return dao.getUserAsDepartmentAssistantManager(userId)
                .stream()
                .map(userDtoMapper)
                .findFirst()
                .orElse(null);
    }


    public BaseUserDto getUserDep(Long userId) {
        BaseUserDto userDto = getUserDepartment(userId);
        if (userDto != null) {
            return getUserDepartment(userId);
        } else {
            return getUserDepartmentAssistantManager(userId);
        }
    }

}
