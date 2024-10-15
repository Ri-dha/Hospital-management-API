package com.azu.hospital.bulding.unit.dao;

import com.azu.hospital.all_user_sevices.employee.doctors.entity.Doctor;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.unit.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("unitRepo")
public class UnitJpaAccess implements UnitDao {

    private final UnitRepository unitRepository;

    @Autowired
    public UnitJpaAccess(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @Override
    public Optional<Unit> findUnitById(Long id) {
        return unitRepository.findById(id);
    }

    @Override
    public List<Unit> findAllUnitByDepartmentId(Long id ) {
        return unitRepository.findAllByDepartmentDepId(id);
    }

    @Override
    public List<Unit> findAllUnitByFloorId(Long id ) {
        return unitRepository.findAllByFloorFloorId(id);
    }

    @Override
    public Page<Unit> findAllUnit(Pageable pageable) {
        return unitRepository.findAll(pageable);
    }

    @Override
    public Unit insertUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public Boolean existsByManagerId(Long managerId) {
        return unitRepository.existsByManagerId(managerId);
    }

    @Override
    public Boolean existsByAssistanceId(Long assistanceId) {
        return unitRepository.existsByManagerAssistanceId(assistanceId);
    }

    @Override
    public void updateUnit(Unit unit) {
         unitRepository.save(unit);
    }

    @Override
    public void deleteUnitById(Long id) {
      unitRepository.deleteById(id);
    }

    @Override
    public Optional<Doctor> getManagerByUnitId(Long unitId) {
        return unitRepository.getManagerByUnitId(unitId);
    }

    @Override
    public Optional<BaseUser> getAssistanceByUnitId(Long unitId) {
        return unitRepository.getManagerAssistanceByUnitId(unitId);
    }
}
