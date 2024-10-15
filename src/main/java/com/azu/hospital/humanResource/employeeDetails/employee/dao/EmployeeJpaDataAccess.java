package com.azu.hospital.humanResource.employeeDetails.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("EmployeeJpaDataAccess")
public class EmployeeJpaDataAccess implements EmployeeDao {
  private final EmployeeJpaRepository repository;

  @Autowired
  public EmployeeJpaDataAccess(EmployeeJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Employee createEmployee(Employee employee) {
    return repository.save(employee);
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    return repository.save(employee);
  }

  @Override
  public Optional<Employee> getById(Long id) {
    return repository.findById(id);
  }

  @Override
  public List<Employee> getRandomEmployee() {
    return repository.findRandomEmployee();
  }

  @Override
  public Page<Employee> getAllStaff(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Override
  public Page<Employee> getEmployeeWithRaise(Pageable pageable) {
    return repository.findEmployeeWithRaise(pageable);
  }

  @Override
  public Page<Employee> getEmployeeWithBonus(Pageable pageable) {
    return repository.findEmployeeWithBonus(pageable);
  }

  @Override
  public Page<Employee> getEmployeeWithPaycat(Pageable pageable) {
    return repository.findEmployeeWithPaycat(pageable);
  }
}
