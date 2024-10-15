package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface SalaryDetailsJpaRepository extends JpaRepository<SalaryDetails, Long> {
  @Query("SELECT salary FROM SalaryDetails salary WHERE salary.employee.employeeId = :employeeId")
  List<SalaryDetails> findAllEmployeeSalaryDetails(@Param("employeeId") Long employeeId);
}
