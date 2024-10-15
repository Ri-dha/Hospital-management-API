package com.azu.hospital.bulding.unit.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.unit.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UnitRepository extends JpaRepository<Unit, Long> {

    List<Unit> findAllByDepartmentDepId(Long department_depId);

    List<Unit> findAllByFloorFloorId(Long department_depId);


    Boolean existsByManagerId(Long manager_id);
    Boolean existsByManagerAssistanceId(Long managerAssistance_id);

    @Query("select u.manager from Unit u where u.unitId = :unitId")
    Optional<Doctor> getManagerByUnitId(Long unitId);

    @Query("select u.managerAssistance from Unit u where u.unitId = :unitId")
    Optional<BaseUser> getManagerAssistanceByUnitId(Long unitId);
}
