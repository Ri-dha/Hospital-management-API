package com.azu.hospital.lab_collection.lab_test_main_table.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableRepository;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class LabTestMainTableUpdateServices {

    private final LabTestMainTableDao mainTableDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public LabTestMainTableUpdateServices(@Qualifier("LabTestMainTableJpa") LabTestMainTableDao mainTableDao, ExceptionsMessageReturn messageReturn) {
        this.mainTableDao = mainTableDao;
        this.messageReturn = messageReturn;
    }

    public void updateExistMainLabTestName(@RequestParam("testId") Long testId,
                                           @RequestParam("testName") String testName){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        LabTestMainTableForMainTestName table = mainTableDao.getTestFromMainTableById(testId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                               messages.getString("resourceNotFound")
                        )
                );
        if (testName != null){
            table.setTestName(testName);
            mainTableDao.updateExistTestFromMainTable(table);
        }
    }
}
