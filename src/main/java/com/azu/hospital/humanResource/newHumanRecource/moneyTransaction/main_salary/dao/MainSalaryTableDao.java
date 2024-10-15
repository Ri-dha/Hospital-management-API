package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;

import java.time.YearMonth;
import java.util.Optional;

public interface MainSalaryTableDao {

    void updateMainSalaryTable(MainSalaryTable request);

    void addMainSalaryTable(MainSalaryTable request);

    Optional<MainSalaryTable> getMainSalaryTableByListId(Long id);

    Optional<MainSalaryTable> getMainSalaryTableByUserId(Long userId);

    Optional<MainSalaryTable> getMainSalaryTableByUserIdAndYearMonth(Long userId, YearMonth yearMonth);
    Optional<MainSalaryTable> getMainSalaryTableByUserIdAndMonth(Long userId, YearMonth month);

}
