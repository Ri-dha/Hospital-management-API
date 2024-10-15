package com.azu.hospital.bulding.unit.services;

import com.azu.hospital.all_user_sevices.employee.doctors.dao.DoctorDao;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDtoMapper;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_dao.UserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDto;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.dto.BaseUserDtoMapper;
import com.azu.hospital.bulding.department.dao.DepartmentDao;
import com.azu.hospital.bulding.department.entity.Department;
import com.azu.hospital.bulding.floor.dao.FloorDao;
import com.azu.hospital.bulding.floor.entity.Floor;
import com.azu.hospital.bulding.unit.dao.UnitDao;
import com.azu.hospital.bulding.unit.dto.UnitDto;
import com.azu.hospital.bulding.unit.dto.UnitDtoMapper;
import com.azu.hospital.bulding.unit.entity.Unit;
import com.azu.hospital.bulding.unit.request.CreateUnitRequest;
import com.azu.hospital.bulding.unit.request.UpdateUnitRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitDao unitDao;
    private final WardDao wardDao;
    private final DepartmentDao departmentDao;
    private final FloorDao floorDao;
    private final UserDao userDao;

    private final BaseUserDao baseUserDao;
    private final DoctorDao doctorDao;

    private final DoctorDtoMapper doctorDtoMapper;

    private final BaseUserDtoMapper baseUserDtoMapper;
    private final UnitDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public UnitService(
            @Qualifier("unitRepo") UnitDao unitDao,
            @Qualifier("depJpa") DepartmentDao departmentDao,
            @Qualifier("floorJpa") FloorDao floorDao,
            @Qualifier("UserJpa") UserDao userDao,
            @Qualifier("BaseUserJpa") BaseUserDao baseUserDao,
            @Qualifier("DoctorJpa") DoctorDao doctorDao,
            @Qualifier("wardRepo") WardDao wardDao, DoctorDtoMapper doctorDtoMapper, BaseUserDtoMapper baseUserDtoMapper,
            UnitDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.unitDao = unitDao;
        this.departmentDao = departmentDao;
        this.floorDao = floorDao;
        this.userDao = userDao;
        this.baseUserDao = baseUserDao;
        this.doctorDao = doctorDao;
        this.doctorDtoMapper = doctorDtoMapper;
        this.baseUserDtoMapper = baseUserDtoMapper;
        this.mapper = mapper;
        this.wardDao = wardDao;
        this.messageReturn = messageReturn;
    }

    @Transactional
    public void createNewUnit(CreateUnitRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        if (unitDao.existsByManagerId(request.getManagerId()) || unitDao.existsByAssistanceId(request.getManagerId())) {
            throw new DuplicateResourceException(
                    messages.getString("existingRoles")
            );
        }

        if (unitDao.existsByManagerId(request.getAssistanceId()) || unitDao.existsByAssistanceId(request.getAssistanceId())) {
            throw new DuplicateResourceException(
                    messages.getString("existingRoles")
            );
        }

        if (wardDao.existsByManagerId(request.getManagerId()) || wardDao.existsByAssistanceId(request.getManagerId())) {
            throw new DuplicateResourceException(
                    messages.getString("existingRoles")
            );
        }

        if (wardDao.existsByManagerId(request.getAssistanceId()) || wardDao.existsByAssistanceId(request.getAssistanceId())) {
            throw new DuplicateResourceException(
                    messages.getString("existingRoles")
            );
        }

        Doctor manager = doctorDao.findDoctorById(request.getManagerId())
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        messages.getString("resourceNotFound")
                                )
                );


        BaseUser assistance = baseUserDao.findById(request.getAssistanceId())
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        messages.getString("resourceNotFound")
                                )
                );

        Department department =
                departmentDao.findDepartmentById(request.getDepartmentId()).orElseThrow(() ->
                        new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        ));

        Floor floor = floorDao.selectFloorById(request.getFloorId()).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));


        Unit unit = new Unit(request.getName());

        unit.setDepartment(department);
        unit.setFloor(floor);
        unit.setManager(manager);
        unit.setManagerAssistance(assistance);
        unit.setDepartment(department);
        floor.getUnits().add(unit);
        department.getUnits().add(unit);
        unitDao.insertUnit(unit);
    }


    public void updateUnit(UpdateUnitRequest request, Long id) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Unit unit = unitDao.findUnitById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        if (request.getName() != null) {
            unit.setName(request.getName());
        }

        if (request.getManagerId() != null) {
            Doctor manager = doctorDao.findDoctorById(request.getManagerId()).orElseThrow(() ->
                    new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    ));

            unit.setManager(manager);
        }


        if (request.getAssistanceId() != null) {
            Doctor assistance = doctorDao.findDoctorById(request.getAssistanceId()).orElseThrow(() ->
                    new ResourceNotFoundException(
                            messages.getString("resourceNotFound")

                    ));

            unit.setManagerAssistance(assistance);
        }

        if (request.getDepartmentId() != null) {
            Department department =
                    departmentDao.findDepartmentById(request.getDepartmentId()).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    messages.getString("resourceNotFound")

                            ));

            unit.setDepartment(department);
            department.setUnits(List.of(unit));

        }


        if (request.getFloorId() != null) {
            Floor floor = floorDao.selectFloorById(request.getFloorId()).orElseThrow(() ->
                    new ResourceNotFoundException(
                            messages.getString("resourceNotFound")

                    ));

            unit.setFloor(floor);
            floor.setUnits(List.of(unit));

        }

        unitDao.updateUnit(unit);
    }


    public List<UnitDto> getAllUnitByDepId(Long departmentId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        departmentDao.getById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")

                ));

        List<Unit> units = unitDao.findAllUnitByDepartmentId(departmentId);
        return units.stream().map(mapper::toDto).collect(Collectors.toList());

    }

    public List<UnitDto> getAllUnitByFloorId(Long floorId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        floorDao.selectFloorById(floorId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        List<Unit> units = unitDao.findAllUnitByFloorId(floorId);
        return units.stream().map(mapper::toDto).collect(Collectors.toList());

    }

    public DoctorDto getManagerByUnitId(Long unitId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        unitDao.findUnitById(unitId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        return doctorDao.findDoctorById(unitId).map(doctorDtoMapper).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

    }

    public BaseUserDto getAssistanceByUnitId(Long unitId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        unitDao.findUnitById(unitId).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

        return baseUserDao.findById(unitId).map(baseUserDtoMapper).orElseThrow(() ->
                new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                ));

    }

}
