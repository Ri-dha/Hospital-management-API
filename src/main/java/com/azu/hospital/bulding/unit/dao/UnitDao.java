package com.azu.hospital.bulding.unit.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.dto.DoctorDto;
import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.unit.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UnitDao {

    Optional<Unit> findUnitById(Long id);

    List<Unit> findAllUnitByDepartmentId(Long id);

    List<Unit> findAllUnitByFloorId(Long id);

    Page<Unit> findAllUnit(Pageable pageable);

    Unit insertUnit(Unit unit);

    Boolean existsByManagerId(Long managerId);
    Boolean existsByAssistanceId(Long assistanceId);
    void updateUnit(Unit unit);

    void deleteUnitById(Long id);

    Optional<Doctor> getManagerByUnitId(Long unitId);

    Optional<BaseUser> getAssistanceByUnitId(Long unitId);
}
