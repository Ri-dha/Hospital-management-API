package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao;

import java.util.List;
import java.util.Optional;

public interface EmployeePunishmentDao {
  EmployeePunishment create(EmployeePunishment punishment);
  void update(EmployeePunishment punishment);
  List<EmployeePunishment> getPunishmentByEmployeeId(Long employeeId);
  Optional<EmployeePunishment> getById(Long punishmentId);

  Long getAllLatePunishment(Long employeeId);

}
