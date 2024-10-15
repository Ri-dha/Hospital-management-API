package com.azu.hospital.accounting.hospitalaccounting.services;

import com.azu.hospital.accounting.hospitalaccounting.dao.HospitalAccountingDao;
import com.azu.hospital.accounting.hospitalaccounting.dto.HospitalAccountingDto;
import com.azu.hospital.accounting.hospitalaccounting.dto.HospitalAccountingDtoMapper;
import com.azu.hospital.accounting.hospitalaccounting.entity.HospitalAccounting;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HospitalAccountingGetServices {

    private final HospitalAccountingDao hospitalAccountingDao;

    private final HospitalAccountingDtoMapper mapper;

    @Autowired
    public HospitalAccountingGetServices(HospitalAccountingDao hospitalAccountingDao, HospitalAccountingDtoMapper mapper) {
        this.hospitalAccountingDao = hospitalAccountingDao;
        this.mapper = mapper;
    }


    public HospitalAccountingDto getHospitalAccountingById(Long id){
        HospitalAccounting hospitalAccounting = hospitalAccountingDao.selectHospitalIncomeById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Accounting Not Found"
                        )
                );

        return mapper.apply(hospitalAccounting);
    }


    public Page<HospitalAccountingDto> getAllWithSearchBy(
            String operationName,
            String patientName,
            boolean isArchived,
            Pageable pageable
    ){
        Page<HospitalAccounting> hospitalAccountingPage = hospitalAccountingDao.selectAllHospitalIncomeAndSearchBy(
                operationName,
                patientName,
                isArchived,
                pageable
        );
        Page<HospitalAccountingDto> dtoPage = hospitalAccountingPage.map(mapper);
         Long count = hospitalAccountingDao.repositoryCount();
        return new PageImpl<>(dtoPage.getContent(), pageable, count);

    }


    public List<HospitalAccountingDto> getAllHospitalIncomeByYearAndMonthAndDate(
            Date date
    ){
        return hospitalAccountingDao.selectAllHospitalIncomeByYearAndMonthAndDate(date)
                .stream()
                .map(mapper)
                .toList();
    }


    public List<HospitalAccountingDto> getAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(
            Date date, boolean isArchived){
        return hospitalAccountingDao.selectAllHospitalIncomeByYearAndMonthAndDateAndIsArchived(
                date,
                isArchived
        ).stream()
                .map(mapper)
                .toList();

    }
}
