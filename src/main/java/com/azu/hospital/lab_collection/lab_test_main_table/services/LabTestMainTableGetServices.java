package com.azu.hospital.lab_collection.lab_test_main_table.services;

import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.lab_collection.lab_test_main_table.dto.LabTestMainTableDto;
import com.azu.hospital.lab_collection.lab_test_main_table.dto.LabTestMainTableDtoMapper;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class LabTestMainTableGetServices {

    private final LabTestMainTableDao mainTableDao;

    private final LabTestMainTableDtoMapper dtoMapper;

    @Autowired
    public LabTestMainTableGetServices(
            @Qualifier("LabTestMainTableJpa") LabTestMainTableDao mainTableDao,
            LabTestMainTableDtoMapper dtoMapper
    ) {
        this.mainTableDao = mainTableDao;
        this.dtoMapper = dtoMapper;
    }

    public LabTestMainTableDto getMainLabTestById(Long testId){
        return mainTableDao.getTestFromMainTableById(testId)
                .map(dtoMapper)
                .orElse(null);
    }


    public Page<LabTestMainTableDto> getAllMainLabTest(@RequestParam(value = "testName") String testName, Pageable pageable){
        Page<LabTestMainTableForMainTestName> testNamePage = mainTableDao.getAllTestesFromMainTableWithNameFilter(testName, pageable);
        List<LabTestMainTableDto> dtoList = testNamePage
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>(dtoList, pageable, testNamePage.getTotalPages());
    }

    public Page<LabTestMainTableDto> getAllMainLabTest(Pageable pageable){
        Page<LabTestMainTableForMainTestName> testNamePage = mainTableDao.getAllWithPrice(pageable);
        List<LabTestMainTableDto> dtoList = testNamePage
                .stream()
                .map(dtoMapper)
                .toList();
        return new PageImpl<>(dtoList, pageable, testNamePage.getTotalPages());
    }
}

