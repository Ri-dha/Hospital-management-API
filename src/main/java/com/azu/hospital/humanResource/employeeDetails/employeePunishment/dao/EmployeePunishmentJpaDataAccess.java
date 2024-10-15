package com.azu.hospital.humanResource.employeeDetails.employeePunishment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository("EmployeePunishmentJpaDataAccess")
public class EmployeePunishmentJpaDataAccess implements EmployeePunishmentDao {
  private final EmployeePunishmentJpaRepository repository;

  @Autowired
  public EmployeePunishmentJpaDataAccess(EmployeePunishmentJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public EmployeePunishment create(EmployeePunishment punishment) {
    return repository.save(punishment);
  }

  @Override
  public void update(EmployeePunishment punishment) {
    repository.save(punishment);
  }

  @Override
  public List<EmployeePunishment> getPunishmentByEmployeeId(Long employeeId) {
    return repository.findAllEmployeePunishment(employeeId);
  }
  @Override
  public Optional<EmployeePunishment> getById(Long punishmentId) {
    return repository.findById(punishmentId);
  }

  @Override
  public Long getAllLatePunishment(Long employeeId) {
    return repository.findAllLatePunishments(employeeId);
  }
}
