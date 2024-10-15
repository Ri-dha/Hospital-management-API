package com.azu.hospital.accounting.hospitalaccounting.dao;

import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public sealed interface HospitalAccountingDao permits HospitalAccountingJpaDataAccess {

    void insertHospitalIncome(HospitalAccounting hospitalAccounting);

    void updateHospitalIncome(HospitalAccounting hospitalAccounting);

    Optional<HospitalAccounting> selectHospitalIncomeById(Long id);

    Page<HospitalAccounting> selectAllHospitalIncomeAndSearchBy(
            String operationName,
            String patientName,
            boolean isArchived,
            Pageable pageable
    );

    List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDate(Date date);

    Double selectTotalHospitalIncomeByYearAndMonthAndDate(Date date);

    Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(Date date, boolean isArchived);

    Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(Date date, boolean isArchived, String operationName);

    List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(Date date, boolean isArchived);


 Long repositoryCount();


}
