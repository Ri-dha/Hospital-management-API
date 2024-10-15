package com.azu.hospital.humanResource.employeeDetails.employee.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
  Employee createEmployee(Employee employee);
  Employee updateEmployee(Employee employee);
  Optional<Employee> getById(Long id);
  List<Employee> getRandomEmployee();
  Page<Employee> getAllStaff(Pageable pageable);
  Page<Employee> getEmployeeWithRaise(Pageable pageable);
  Page<Employee> getEmployeeWithBonus(Pageable pageable);
  Page<Employee> getEmployeeWithPaycat(Pageable pageable);
}
