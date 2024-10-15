package com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.service;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.dao.CustomSalaryDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.entity.CustomSalary;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.customSalary.request.CustomSalaryUpdate;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.dao.MainSalaryTableDao;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.entity.MainSalaryTable;
import com.azu.hospital.humanResource.newHumanRecource.moneyTransaction.main_salary.request.MainSalaryTableUpdateRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.*;

@Service
public class MainSalaryTableUpdateService {
    private final MainSalaryTableDao dao;
    private final ExceptionsMessageReturn messageReturn;
    private final CustomSalaryDao customSalaryDao;

    @Autowired
    public MainSalaryTableUpdateService(MainSalaryTableDao dao, ExceptionsMessageReturn messageReturn, CustomSalaryDao customSalaryDao) {
        this.dao = dao;
        this.messageReturn = messageReturn;
        this.customSalaryDao = customSalaryDao;
    }

    public void updateMainSalaryTable(Long id, MainSalaryTableUpdateRequest request) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        MainSalaryTable mainSalaryTable = dao.getMainSalaryTableByListId(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getString("resourceNotFound") + " " + id
                ));

        YearMonth currentMonth = YearMonth.now();
        if (!mainSalaryTable.getYearMonth().equals(currentMonth)) {
            throw new BadRequestException(
                    messages.getString("cannotAccept" + " " + id + " " + "invalidDate")
            );
        }

        List<CustomSalary> changedItems = new ArrayList<>();
        for (CustomSalaryUpdate update : request.bonusesAndDeductions()) {
            if (update.id() != null) {
                CustomSalary customSalary = customSalaryDao.getCustomSalaryById(update.id())
                        .orElseThrow(() -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound") + " " + update.id()
                        ));

                if (update.amount() != null) {
                    customSalary.setAmount(update.amount());
                }
                if (update.title() != null) {
                    customSalary.setTitle(update.title());
                }
                if (update.isDown() != null) {
                    customSalary.setIsDown(update.isDown());
                }
                if (update.note() != null) {
                    customSalary.setNote(update.note());
                }
                if (update.isDeleted() != null) {
                    customSalary.setIsDeleted(update.isDeleted());
                }

                customSalaryDao.updateCustomSalary(customSalary);
                changedItems.add(customSalary);
            } else {
                CustomSalary newCustomSalary = new CustomSalary(
                        update.title(),
                        update.amount(),
                        update.isDown(),
                        update.note()
                );
                newCustomSalary.setMainSalaryTableList(List.of(mainSalaryTable));
                customSalaryDao.createCustomSalary(newCustomSalary);
                changedItems.add(newCustomSalary);
            }
        }
        Double customSalary = customSalaryDao.getSumsCustomSalaryByMainSalaryId(mainSalaryTable.getMainSalaryId());
        mainSalaryTable.setTotalSalary(customSalary);
        dao.updateMainSalaryTable(mainSalaryTable);
    }
    /**
     * 1. get main salary table by id
     * 2. check if the year month is the current month
     * 3. update custom salary by id if it exists
     * 4. create custom salary if the id does not exist
     * 5. update main salary table
     * 6. return void
     **/


}
