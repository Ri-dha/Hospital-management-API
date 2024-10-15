package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("SalaryDetailsJpaDataAccess")
public class SalaryDetailsJpaDataAccess implements SalaryDetailsDao {

  private final SalaryDetailsJpaRepository repository;

  @Autowired
  public SalaryDetailsJpaDataAccess(SalaryDetailsJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public SalaryDetails create(SalaryDetails details) {
    return repository.save(details);
  }

  @Override
  public void update(SalaryDetails details) {
    repository.save(details);
  }

  @Override
  public Optional<SalaryDetails> getById(Long id) {
    return repository.findById(id);
  }

  @Override
  public void delete(SalaryDetails details) {
    repository.delete(details);
  }

  @Override
  public List<SalaryDetails> getAllEmployeeSalaryDetails(Long employeeId) {
    return repository.findAllEmployeeSalaryDetails(employeeId);
  }
}
