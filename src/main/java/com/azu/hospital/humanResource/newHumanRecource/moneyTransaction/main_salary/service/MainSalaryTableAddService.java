package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service;


import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUser;
import com.azu.hospital.all_user_sevices.user_utils.base_user_service.BaseUserDao;
import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao.CustomSalaryDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request.CustomSalaryRequest;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao.MainSalaryTableDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request.MainSalaryTableAddRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSalaryTableAddService {
    private final MainSalaryTableDao mainSalaryTableDao;
    private final BaseUserDao baseUserDao;
    private final ExceptionsMessageReturn messageReturn;

    private final CustomSalaryDao customSalaryDao;


    @Autowired
    public MainSalaryTableAddService(MainSalaryTableDao mainSalaryTableDao, BaseUserDao baseUserDao, ExceptionsMessageReturn messageReturn, CustomSalaryDao customSalaryDao) {
        this.mainSalaryTableDao = mainSalaryTableDao;
        this.baseUserDao = baseUserDao;
        this.messageReturn = messageReturn;

        this.customSalaryDao = customSalaryDao;
    }

    public void addMainSalaryTable(Long userId, MainSalaryTableAddRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        BaseUser user = baseUserDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(messages.getString("userNotFound"))
        );
        LocalDate currentDate = LocalDate.now();
        YearMonth currentYearMonth = YearMonth.from(currentDate);

        if (mainSalaryTableDao.getMainSalaryTableByUserIdAndYearMonth(userId, currentYearMonth).isPresent()) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyExist") + " " + currentYearMonth);
        }

        MainSalaryTable mainSalaryTable = new MainSalaryTable();
        List<CustomSalary> customSalaries = new ArrayList<>();

        for (CustomSalaryRequest customSalary : request.bonusesAndDeductions()) {
            CustomSalary salary = new CustomSalary(
                    customSalary.title(),
                    customSalary.amount(),
                    customSalary.isDown(),
                    customSalary.note(),
                    false
            );
            customSalaries.add(salary);
            mainSalaryTable.setCustomSalaries(customSalaries);
        }

        mainSalaryTable.setYearMonth(currentYearMonth);
        customSalaryDao.createListCustomSalary(customSalaries);
        mainSalaryTable.setUsers(user);
        mainSalaryTableDao.addMainSalaryTable(mainSalaryTable);
        Double totalSalary = customSalaryDao.getSumsCustomSalaryByMainSalaryId(mainSalaryTable.getMainSalaryId());
        mainSalaryTable.setTotalSalary(totalSalary);
        mainSalaryTableDao.updateMainSalaryTable(mainSalaryTable);
    }

}





