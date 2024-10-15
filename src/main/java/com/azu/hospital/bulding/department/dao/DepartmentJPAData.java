package com.azu.hospital.bulding.department.dao;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.department.entity.Department;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("depJpa")
@Qualifier("depJpa")
public class DepartmentJPAData implements DepartmentDao{
    private final DepartmentRepository departmentRepository;

    public DepartmentJPAData(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department createDepartment(Department request) {
        return departmentRepository.save(request);
    }

    @Override
    public Optional<Department> getById(Long depId) {
        return departmentRepository.findById(depId);
    }

    @Override
    public void updateDepartment(Department update) {
        departmentRepository.save(update);
    }

    @Override
    public void deleteDepartment(Long depId) {
        departmentRepository.deleteById(depId);
    }

    @Override
    public Page<Department> getAllDepartment(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public boolean findDepartmentByDepName(String debName) {
        return departmentRepository.existsDepartmentsByDepartmentNameContainingIgnoreCase(debName);
    }

    @Override
    public Boolean existsByManagerId(Long managerId) {
        return departmentRepository.existsByManagerId(managerId);
    }

    @Override
    public Boolean existsByAssistanceManagerId(Long assistanceId) {
        return departmentRepository.existsByDepartmentMangerAssistanceId(assistanceId);
    }

    @Override
    public Optional<BaseUser> getUserAsDepartmentManager(Long userId) {
        return departmentRepository.findUserAsDepartmentManager(userId);
    }

    @Override
    public Optional<BaseUser> getUserAsDepartmentAssistantManager(Long userId) {
        return departmentRepository.findUserAsDepartmentAssistantManager(userId);
    }



}
