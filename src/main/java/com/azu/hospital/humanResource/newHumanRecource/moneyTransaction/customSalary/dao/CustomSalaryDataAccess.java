package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository("customSalaryJpa")
public class CustomSalaryDataAccess implements CustomSalaryDao{
  private final CustomSalaryRepository customSalaryRepository;

  public CustomSalaryDataAccess(CustomSalaryRepository customSalaryRepository) {
    this.customSalaryRepository = customSalaryRepository;
  }

  @Override
  public Optional<CustomSalary> getCustomSalaryById(Long id) {
    return customSalaryRepository.findById(id);
  }

  @Override
  public void createCustomSalary(CustomSalary salary) {
    customSalaryRepository.save(salary);
  }

  @Override
  public void createListCustomSalary(List<CustomSalary> customSalaries) {
    customSalaryRepository.saveAll(customSalaries);
  }

  @Override
  public void createListCustomSalaryByMainSalaryId(List<CustomSalary> customSalaries, Long mainSalaryId) {
    customSalaryRepository.saveAll(customSalaries);
  }

  @Override
  public List<CustomSalary> getCustomSalariesByUserId(Long userId) {
    return customSalaryRepository.getCustomSalariesByBaseUserId(userId);
  }

  @Override
  public void updateCustomSalary(CustomSalary customSalary) {
    customSalaryRepository.save(customSalary);
  }

  @Override
  public void deleteCustomSalary(Long salaryId) {
    customSalaryRepository.deleteById(salaryId);
  }

  @Override
  public List<CustomSalary> getAllCustomSalaryByDate(Long userId, Instant date) {
    return customSalaryRepository.getAllByDate(userId,date);
  }

  @Override
  public Double getSumCustomSalaryByMonth(Long userId) {
    return customSalaryRepository.getSumCustomSalariesByMonth(userId);
  }

  @Override
  public Double getSumsCustomSalaryByMainSalaryId(Long mainSalaryId) {
    return customSalaryRepository.getSumCustomSalariesByMainSalaryId(mainSalaryId);
  }

}
