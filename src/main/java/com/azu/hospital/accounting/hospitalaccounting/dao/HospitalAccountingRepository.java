package com.azu.hospital.accounting.hospitalaccounting.dao;

import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Transactional
public interface HospitalAccountingRepository extends JpaRepository<HospitalAccounting, Long>{


    @Query("SELECT ha FROM HospitalAccounting ha " +
            "WHERE (:operationName IS NULL OR ha.operationName = :operationName) " +
            "AND (:patientName IS NULL OR ha.patientName = :patientName) " +
            "AND (:isArchived IS NULL OR ha.isArchived = :isArchived)")
    Page<HospitalAccounting> selectAllHospitalIncomeAndSearchBy(
            @Param("operationName") String operationName,
            @Param("patientName") String patientName,
            @Param("isArchived") boolean isArchived,
            Pageable pageable
    );


    @Query("SELECT ha FROM HospitalAccounting ha " +
            "WHERE YEAR(ha.date) = YEAR(:date) " +
            "AND MONTH(ha.date) = MONTH(:date) " +
            "AND DAY(ha.date) = DAY(:date)")
    List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDate(@Param("date") Date date);



    @Query("SELECT SUM(ha.hospitalIncome) FROM HospitalAccounting ha " +
            "WHERE YEAR(ha.date) = YEAR(:date) " +
            "AND MONTH(ha.date) = MONTH(:date) " +
            "AND DAY(ha.date) = DAY(:date)")
    Double selectTotalHospitalIncomeByYearAndMonthAndDate(@Param("date") Date date);


    @Query("SELECT SUM(ha.hospitalIncome) FROM HospitalAccounting ha " +
            "WHERE YEAR(ha.date) = YEAR(:date) " +
            "AND MONTH(ha.date) = MONTH(:date) " +
            "AND DAY(ha.date) = DAY(:date) " +
            "AND ha.isArchived = :isArchived")
    Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(
            @Param("date") Date date,@Param("isArchived") boolean isArchived);



    @Query("SELECT SUM(ha.hospitalIncome) FROM HospitalAccounting ha " +
            "WHERE YEAR(ha.date) = YEAR(:date) " +
            "AND MONTH(ha.date) = MONTH(:date) " +
            "AND DAY(ha.date) = DAY(:date) " +
            "AND ha.isArchived = :isArchived " +
            "AND ha.operationName = :operationName")
    Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(
            @Param("date") Date date,
            @Param("isArchived") boolean isArchived,
            @Param("operationName") String operationName);



    @Query("SELECT ha FROM HospitalAccounting ha " +
            "WHERE YEAR(ha.date) = YEAR(:date) " +
            "AND MONTH(ha.date) = MONTH(:date) " +
            "AND DAY(ha.date) = DAY(:date) " +
            "AND ha.isArchived = :isArchived")
    List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(
            @Param("date") Date date,
            @Param("isArchived") boolean isArchived);
}
