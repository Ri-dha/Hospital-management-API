package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service;

import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao.MainSalaryTableDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto.MainSalaryTableDto;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto.MainSalaryTableDtoMapper;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSalaryTableGetService {

    private final MainSalaryTableDao dao;
    private final ExceptionsMessageReturn messageReturn;


    private final MainSalaryTableDtoMapper mapper;

    public MainSalaryTableGetService(MainSalaryTableDao dao, ExceptionsMessageReturn messageReturn, MainSalaryTableDtoMapper mapper) {
        this.dao = dao;
        this.messageReturn = messageReturn;
        this.mapper = mapper;
    }



    public MainSalaryTableDto getByUserIdAndMonth(Long userId, YearMonth month) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        return dao.getMainSalaryTableByUserIdAndMonth(userId, month)
                .stream()
                .map(mapper)
                .findFirst()
                .orElse(null);

    }


}
