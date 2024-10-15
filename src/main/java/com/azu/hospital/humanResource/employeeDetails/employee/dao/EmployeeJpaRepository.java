package com.azu.hospital.humanResource.employeeDetails.employee.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

  @Query(value = "SELECT * FROM employee ORDER BY random() LIMIT 9", nativeQuery = true)
  List<Employee> findRandomEmployee();

  @Query("SELECT e FROM Employee e WHERE e.status = 'RAISE'")
  Page<Employee> findEmployeeWithRaise(Pageable pageable);
  @Query("SELECT e FROM Employee e WHERE e.status = 'PAYCAT'")
  Page<Employee> findEmployeeWithPaycat(Pageable pageable);
  @Query("SELECT e FROM Employee e WHERE e.status = 'BONUS'")
  Page<Employee> findEmployeeWithBonus(Pageable pageable);
}
