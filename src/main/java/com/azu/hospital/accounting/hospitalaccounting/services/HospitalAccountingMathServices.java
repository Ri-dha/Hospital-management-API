package com.azu.hospital.accounting.hospitalaccounting.services;

import com.azu.hospital.accounting.hospitalaccounting.dao.HospitalAccountingDao;
import com.azu.hospital.accounting.hospitalaccounting.dto.HospitalAccountingDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HospitalAccountingMathServices {



    private final HospitalAccountingDao hospitalAccountingDao;

    private final HospitalAccountingDtoMapper mapper;


    @Autowired
    public HospitalAccountingMathServices(HospitalAccountingDao hospitalAccountingDao, HospitalAccountingDtoMapper mapper) {
        this.hospitalAccountingDao = hospitalAccountingDao;
        this.mapper = mapper;
    }


    public Double getTotalHospitalIncomeByYearAndMonthAndDate(Date date){
        return hospitalAccountingDao.selectTotalHospitalIncomeByYearAndMonthAndDate(date);
    }


    public Double getTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(Date date, boolean isArchived){
        return hospitalAccountingDao.selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchived(
                date,
                isArchived
        );
    }



    public Double selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(
            Date date, boolean isArchived, String operationName){
        return hospitalAccountingDao.selectTotalHospitalIncomeByYearAndMonthAndDateAndIsArchivedAndOperationName(
                date,
                isArchived,
                operationName
        );
    }


}
