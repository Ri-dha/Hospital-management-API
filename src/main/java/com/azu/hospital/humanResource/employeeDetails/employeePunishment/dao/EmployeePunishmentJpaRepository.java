package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeePunishmentJpaRepository extends JpaRepository<EmployeePunishment, Long> {

  @Query("SELECT punishment FROM EmployeePunishment punishment WHERE punishment.employee.employeeId = :employeeId")
  List<EmployeePunishment> findAllEmployeePunishment(@Param("employeeId") Long employeeId);

  @Query("SELECT count(punishment) FROM EmployeePunishment punishment WHERE punishment.employee.employeeId = :employeeId AND punishment.punishmentType = 'LATE'")
  Long findAllLatePunishments(@Param("employeeId") Long employeeId);
}
