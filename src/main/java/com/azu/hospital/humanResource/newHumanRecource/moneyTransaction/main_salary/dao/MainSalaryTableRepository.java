package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.YearMonth;
import java.util.Optional;

@Transactional
public interface MainSalaryTableRepository extends JpaRepository<MainSalaryTable, Long> {

    @Query("SELECT mst FROM MainSalaryTable mst WHERE mst.users.id = :userId")
    Optional<MainSalaryTable> getAllByUserId(Long userId);

    @Query("SELECT mst FROM MainSalaryTable mst WHERE mst.users.id = :userId AND mst.yearMonth = :yearMonth")
    Optional<MainSalaryTable> getAllByUserIdAndYearMonth(Long userId, YearMonth yearMonth);
}
