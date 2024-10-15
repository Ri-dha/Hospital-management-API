package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao;


import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.YearMonth;
import java.util.List;


@Transactional
public interface CustomSalaryRepository extends JpaRepository<CustomSalary,Long> {

    @Query("SELECT cs FROM CustomSalary cs WHERE cs.baseUser.id = :userId ORDER BY COALESCE(cs.updatedAt,cs.createdAt) DESC")
    List<CustomSalary> getCustomSalariesByBaseUserId(Long userId);

    @Query("SELECT cs FROM CustomSalary cs WHERE cs.baseUser.id = :userId " +
            "AND EXTRACT(YEAR FROM cs.createdAt) = EXTRACT(YEAR FROM CAST(:date AS TIMESTAMP))" +
            "AND EXTRACT(MONTH FROM cs.createdAt) = EXTRACT(MONTH FROM CAST(:date AS TIMESTAMP ))")
    List<CustomSalary> getAllByDate(@Param("userId") Long userId , @Param("date") Instant date);

    @Query("SELECT SUM(CASE WHEN cs.isDown=true THEN -cs.amount ELSE cs.amount END ) FROM CustomSalary cs " +
            " WHERE cs.baseUser.id = :userId ")
    Double getSumCustomSalariesByMonth(Long userId);

    @Query("SELECT SUM(CASE WHEN cs.isDown=true THEN -cs.amount ELSE cs.amount END) " +
            "FROM CustomSalary cs " +
            "JOIN cs.mainSalaryTableList mainSalaryTable " +
            "WHERE mainSalaryTable.mainSalaryId = :mainSalaryId And cs.isDeleted = false")
    Double getSumCustomSalariesByMainSalaryId(Long mainSalaryId);


}
