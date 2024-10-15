package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Optional;
@Repository("MainSalaryTableJpa")
public class MainSalaryTableJpaDataAccess implements MainSalaryTableDao{

    private final MainSalaryTableRepository repository;

    @Autowired
    public MainSalaryTableJpaDataAccess(MainSalaryTableRepository repository) {
        this.repository = repository;
    }


    @Override
    public void updateMainSalaryTable(MainSalaryTable request) {
           repository.save(request);
    }

    @Override
    public void addMainSalaryTable(MainSalaryTable request) {
        repository.save(request);
    }

    @Override
    public Optional<MainSalaryTable> getMainSalaryTableByListId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<MainSalaryTable> getMainSalaryTableByUserId(Long userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public Optional<MainSalaryTable> getMainSalaryTableByUserIdAndYearMonth(Long userId, YearMonth yearMonth) {
        return repository.getAllByUserIdAndYearMonth(userId, yearMonth);
    }

    @Override
    public Optional<MainSalaryTable> getMainSalaryTableByUserIdAndMonth(Long userId, YearMonth month) {
        return repository.getAllByUserIdAndYearMonth(userId, month);
    }
}
