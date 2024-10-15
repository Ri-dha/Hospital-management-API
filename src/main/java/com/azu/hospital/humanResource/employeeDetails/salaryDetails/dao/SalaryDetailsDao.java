package com.azu.hospital.humanResource.employeeDetails.salaryDetails.dao;

import java.util.List;
import java.util.Optional;

public interface SalaryDetailsDao {
  SalaryDetails create(SalaryDetails details);
  void update(SalaryDetails details);
  Optional<SalaryDetails> getById(Long id);
  void delete(SalaryDetails details);

  List<SalaryDetails> getAllEmployeeSalaryDetails(Long employeeId);
}
