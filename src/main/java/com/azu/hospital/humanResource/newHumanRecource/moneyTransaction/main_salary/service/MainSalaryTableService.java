package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service;

import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao.CustomSalaryDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao.MainSalaryTableDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dto.MainSalaryDto;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSalaryTableService {
    private final BaseUserDao baseUserDao;

    private final MainSalaryTableDao mainSalaryTableDao;
    private final CustomSalaryDao customSalaryDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public MainSalaryTableService(BaseUserDao baseUserDao, MainSalaryTableDao mainSalaryTableDao, CustomSalaryDao customSalaryDao, ExceptionsMessageReturn messageReturn) {
        this.baseUserDao = baseUserDao;
        this.mainSalaryTableDao = mainSalaryTableDao;
        this.customSalaryDao = customSalaryDao;
        this.messageReturn = messageReturn;
    }

    public MainSalaryDto getSalaryComponent(Long userId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        BaseUser baseUser = baseUserDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + " " + userId
                )
        );

        MainSalaryTable mainSalaryTable = mainSalaryTableDao.getMainSalaryTableByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + " " + userId
                )
        );

        Double customSalary = customSalaryDao.getSumsCustomSalaryByMainSalaryId(mainSalaryTable.getMainSalaryId());

        Double totalSalary =
                (customSalary== null ? 0L :customSalary );

        mainSalaryTable.setTotalSalary(totalSalary);
        mainSalaryTableDao.updateMainSalaryTable(mainSalaryTable);

        return new MainSalaryDto(
                totalSalary
        );
    }
}

