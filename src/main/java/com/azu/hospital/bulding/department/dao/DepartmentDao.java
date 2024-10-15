package com.azu.hospital.bulding.department.dao;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.department.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DepartmentDao {


    Optional<Department> findDepartmentById(Long id);
    Department createDepartment(Department request);
    Optional<Department> getById(Long depId);
    void updateDepartment(Department update);
    void deleteDepartment(Long depId);
    Page<Department> getAllDepartment(Pageable pageable);
    boolean findDepartmentByDepName(String debName);

    Boolean existsByManagerId(Long managerId);
    Boolean existsByAssistanceManagerId(Long assistanceId);

    Optional<BaseUser> getUserAsDepartmentManager(Long userId);


    Optional<BaseUser> getUserAsDepartmentAssistantManager(Long userId);

}
