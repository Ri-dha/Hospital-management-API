package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;

import java.time.Instant;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface CustomSalaryDao {

  Optional<CustomSalary> getCustomSalaryById(Long id);

  void createCustomSalary(CustomSalary salary);

  void createListCustomSalary(List<CustomSalary> customSalaries);

  void createListCustomSalaryByMainSalaryId(List<CustomSalary> customSalaries, Long mainSalaryId);

  List<CustomSalary> getCustomSalariesByUserId(Long userId);

 void updateCustomSalary(CustomSalary customSalary);

 void deleteCustomSalary(Long salaryId);

  List<CustomSalary> getAllCustomSalaryByDate(Long userId, Instant date);
  Double getSumCustomSalaryByMonth(Long userId);

  Double getSumsCustomSalaryByMainSalaryId(Long mainSalaryId);

}
