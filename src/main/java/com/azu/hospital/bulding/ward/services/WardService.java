package com.azu.hospital.bulding.ward.services;

import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDtoMapper;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.employee.nurses.dao.NurseDao;
import com.azu.hospital.all_user_sevices.employee.nurses.dto.NurseDto;
import com.azu.hospital.all_user_sevices.employee.nurses.dto.NurseDtoMapper;
import com.azu.hospital.all_user_sevices.employee.nurses.entity.Nurse;
import com.azu.hospital.all_user_sevices.roles_sevices.Role;
import com.azu.hospital.all_user_sevices.roles_sevices.dao.RoleDao;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.dto.WardDto;
import com.azu.hospital.bulding.ward.dto.WardDtoMapper;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.request.CreateWardRequest;
import com.azu.hospital.bulding.ward.request.UpdateWardRequest;
import com.azu.hospital.bulding.department.dao.DepartmentDao;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.floor.dao.FloorDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.security.newsecurity.token.DeleteAllTokenServices;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.EnumDoctorRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WardService {


    private final WardDao wardDao;
    private final UnitDao unitDao;
    private final DepartmentDao departmentDao;
    private final NurseDao nurseDao;
    private final WardDtoMapper mapper;
    private final DoctorDao doctorDao;
    private final FloorDao floorDao;
    private final ExceptionsMessageReturn messageReturn;
    private final DoctorDtoMapper doctorDtoMapper;
    private final NurseDtoMapper nurseDtoMapper;
    private final RoleDao roleDao;

    private final DeleteAllTokenServices deleteAllTokenServices;

    public WardService(
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("depJpa") DepartmentDao departmentDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            @Qualifier("unitRepo") UnitDao unitDao,
            @Qualifier("NurseJpa") NurseDao nurseDao,
            WardDtoMapper mapper,

            FloorDao floorDao, ExceptionsMessageReturn messageReturn, DoctorDtoMapper doctorDtoMapper, NurseDtoMapper nurseDtoMapper, RoleDao roleDao, DeleteAllTokenServices deleteAllTokenServices) {
        this.wardDao = wardDao;
        this.departmentDao = departmentDao;
        this.nurseDao = nurseDao;
        this.mapper = mapper;
        this.doctorDao = doctorDao;
        this.unitDao = unitDao;
        this.floorDao = floorDao;
        this.messageReturn = messageReturn;
        this.doctorDtoMapper = doctorDtoMapper;
        this.nurseDtoMapper = nurseDtoMapper;
        this.roleDao = roleDao;
        this.deleteAllTokenServices = deleteAllTokenServices;
    }


    @Transactional
    public void createNewWard(CreateWardRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (wardDao.existsByManagerId(request.getManagerId()) || wardDao.existsByAssistanceId(request.getManagerId())) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyWardManager") + " " + request.getManagerId()
            );
        }

        if (wardDao.existsByManagerId(request.getAssistanceId()) || wardDao.existsByAssistanceId(request.getAssistanceId())) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyWardAssistant") + " " + request.getAssistanceId()
            );
        }

        if (unitDao.existsByManagerId(request.getManagerId()) || unitDao.existsByAssistanceId(request.getManagerId())) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyUnitManager") + " " + request.getManagerId()
            );
        }

        if (unitDao.existsByManagerId(request.getAssistanceId()) || unitDao.existsByAssistanceId(request.getAssistanceId())) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyUnitAssistant") + " " + request.getAssistanceId()
            );
        }

        Doctor manager = doctorDao.findDoctorById(request.getManagerId())
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        messages.getString("userNotFound") + request.getManagerId()
                                ));


        Nurse nurse = nurseDao.findNurseById(request.getAssistanceId())
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        messages.getString("userNotFound") + request.getAssistanceId()
                                ));

        Department department =
                departmentDao.findDepartmentById(request.getDepartmentId()).orElseThrow(() ->
                        new ResourceNotFoundException(
                                messages.getString("departmentNotFound") + request.getDepartmentId()
                        ));

        Floor floor = floorDao.selectFloorById(request.getFloorId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("floorNotFound") + request.getFloorId()
                        )
                );

        Ward ward = new Ward(request.getName());

        ward.setNurse(nurse);
        Set<Role> roles = nurse.getRoles();
        Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER_ASSISTANT.name()));
        roles.addAll(role);
        deleteAllTokenServices.deleteAllTokens(nurse.getId());

        ward.setDoctor(manager);
        Set<Role> roles1 = manager.getRoles();
        Set<Role> role1 = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER.name()));
        roles1.addAll(role1);
        deleteAllTokenServices.deleteAllTokens(manager.getId());

        ward.setDepartment(department);
        ward.setFloor(floor);
        floor.getWards().add(ward);
//        floor.setWards(List.of(ward));
        department.getWards().add(ward);
//        department.setWards(List.of(ward));

        if (request.getManagerId() == null) {
            ward.setDoctor(department.getManager());
        }
        wardDao.createWard(ward);
    }


    public void updateWard(UpdateWardRequest request, Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Ward ward = wardDao.findWardById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("wardNotFound") + " " + id
                ));
        boolean changes = false;

        Doctor existManager = ward.getDoctor();
        Nurse existAssistance = ward.getNurse();

        if (existManager != null) {
            Set<Role> roles = existManager.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER.name()));
            roles.removeAll(role);
            existManager.setDepartmentManger(null);
            deleteAllTokenServices.deleteAllTokens(existManager.getId());
        }

        if (existAssistance != null) {
            Set<Role> roles = existAssistance.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER_ASSISTANT.name()));
            roles.removeAll(role);
            existAssistance.setDepartmentMangerAssistance(null);
            deleteAllTokenServices.deleteAllTokens(existAssistance.getId());
        }


        if (request.getName() != null) {
            ward.setName(request.getName());
            changes = true;
        }
        if (request.getManagerId() != null) {
            Doctor manager = doctorDao.findDoctorById(request.getManagerId()).orElseThrow(() ->
                    new ResourceNotFoundException(
                            messages.getString("userNotFound") + " " + request.getManagerId()
                    ));
            Set<Role> roles = manager.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER.name()));
            roles.addAll(role);

            manager.setWard(ward);
            ward.setDoctor(manager);
            changes = true;
        }


        if (request.getAssistanceId() != null) {
            Nurse assistance = nurseDao.findNurseById(request.getAssistanceId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    messages.getString("userNotFound") + " " + request.getAssistanceId()
                            ));
            Set<Role> roles = assistance.getRoles();
            Set<Role> role = roleDao.findRoleByRoleName(Set.of(EnumDoctorRole.WARD_MANAGER_ASSISTANT.name()));
            roles.addAll(role);

            assistance.setWard(ward);
            ward.setNurse(assistance);
            changes = true;
        }

        if (request.getDepartmentId() != null) {
            Department department =
                    departmentDao.findDepartmentById(request.getDepartmentId()).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    messages.getString("departmentNotFound") + " " + request.getDepartmentId()
                            ));

            ward.setDepartment(department);
            department.setWards(List.of(ward));
            changes = true;

        }
        if (request.getFloorId() != null) {
            Floor floor = floorDao.selectFloorById(request.getFloorId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    messages.getString("resourceNotFound") + request.getFloorId()
                            )
                    );
            if (ward.getFloor() != null) {
                ward.getFloor().getWards().remove(ward);
            }
            ward.setFloor(floor);
            floor.getWards().add(ward);
            changes = true;
        }
        if (!changes) {
            throw new DuplicateResourceException(
                    messages.getString("noChanges")
            );
        }

        wardDao.updateWard(ward);
    }

    public List<WardDto> getAllWardByDepId(Long departmentId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Department department = departmentDao.getById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("departmentNotFound") + " " + departmentId
                ));

        return wardDao.findAllWardByDepartmentId(department.getDepId())
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

    }

    public List<WardDto> getAllWardByFloorId(Long floorId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        floorDao.selectFloorById(floorId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("floorNotFound") + " " + floorId
                ));

        List<Ward> wards = wardDao.getAllWardByFloorId(floorId);
        return wards
                .stream()
                .map(mapper)
                .collect(Collectors.toList());

    }


    public Page<WardDto> getAllWard(String wardName, Pageable pageable) {
        return wardDao.findAllWard(wardName, pageable).map(mapper);
    }

    public List<DoctorDto> getDoctorsByWardId(Long wardId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        wardDao.findWardById(wardId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("wardNotFound") + " " + wardId
                ));

        return wardDao.getDoctorsByWardId(wardId)
                .stream()
                .map(doctorDtoMapper)
                .collect(Collectors.toList());
    }

    public List<NurseDto> getNursesByWardId(Long wardId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        return wardDao.getNursesByWardId(wardId)
                .stream()
                .map(nurseDtoMapper)
                .collect(Collectors.toList());
    }


    public WardDto getWardByNurseId(Long nurseId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        return wardDao.getWardBuNurseId(nurseId)
                .map(mapper)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                messages.getString("wardNotFound") + " " + nurseId
                        ));
    }
}
