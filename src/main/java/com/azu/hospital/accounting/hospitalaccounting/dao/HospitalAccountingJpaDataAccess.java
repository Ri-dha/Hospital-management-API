package com.azu.hospital.accounting.hospitalaccounting.dao;

import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository("HospitalAccountingJpa")
public non-sealed class HospitalAccountingJpaDataAccess implements HospitalAccountingDao{

    private final HospitalAccountingRepository hospitalAccountingRepository;

    @Autowired
    public HospitalAccountingJpaDataAccess(HospitalAccountingRepository hospitalAccountingRepository) {
        this.hospitalAccountingRepository = hospitalAccountingRepository;
    }

    @Override
    public void insertHospitalIncome(HospitalAccounting hospitalAccounting) {
        hospitalAccountingRepository.save(hospitalAccounting);
    }

    @Override
    public void updateHospitalIncome(HospitalAccounting hospitalAccounting) {
        hospitalAccountingRepository.save(hospitalAccounting);
    }

    @Override
    public Optional<HospitalAccounting> selectHospitalIncomeById(Long id) {
        return hospitalAccountingRepository.findById(id);
    }

    @Override
    public Page<HospitalAccounting> selectAllHospitalIncomeAndSearchBy(
            String operationName, String patientName, boolean isArchived, Pageable pageable) {
        return hospitalAccountingRepository.selectAllHospitalIncomeAndSearchBy(
                operationName,
                patientName,
                isArchived,
                pageable
        );
    }

    @Override
    public List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDate(Date date) {
        return hospitalAccountingRepository.selectAllHospitalIncomeByYearAndMonthAndDate(date);
    }

    @Override
    public Double selectTotalHospitalIncomeByYearAndMonthAndDate(Date date) {
        return hospitalAccountingRepository.selectTotalHospitalIncomeByYearAndMonthAndDate(date);
    }

    @Override
    public Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(
            Date date, boolean isArchived) {
        return hospitalAccountingRepository.selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(
                date, isArchived
        );
    }

    @Override
    public Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(
            Date date, boolean isArchived, String operationName) {
        return hospitalAccountingRepository.selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(
                date, isArchived, operationName
        );
    }

    @Override
    public List<HospitalAccounting> selectAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(
            Date date, boolean isArchived) {
        return hospitalAccountingRepository.selectAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(
                date, isArchived
        );
    }

    @Override
    public Long repositoryCount() {
        return hospitalAccountingRepository.count();
    }
}
