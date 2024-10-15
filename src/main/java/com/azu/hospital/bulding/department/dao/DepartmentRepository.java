package com.azu.hospital.bulding.department.dao;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.bulding.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsDepartmentsByDepartmentNameContainingIgnoreCase(String debName);

    Boolean existsByManagerId(Long managerId);
    Boolean existsByDepartmentMangerAssistanceId(Long assistanceId);

    @Query("SELECT d.manager FROM Department d WHERE d.manager.id = :userId")
    Optional<BaseUser> findUserAsDepartmentManager(@Param("userId") Long userId);

    @Query("SELECT d.departmentMangerAssistance FROM Department d WHERE d.departmentMangerAssistance.id = :userId")
    Optional<BaseUser> findUserAsDepartmentAssistantManager(@Param("userId")Long userId);


}
