package com.azu.hospital.lab_collection.lab_test_main_table.services;

import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.lab_collection.lab_test_main_table.dao.LabTestMainTableDao;
import com.azu.hospital.lab_collection.lab_test_main_table.entity.LabTestMainTableForMainTestName;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class LabTestMainTableAddServices {

    private final LabTestMainTableDao mainTableDao;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public LabTestMainTableAddServices(LabTestMainTableDao mainTableDao, ExceptionsMessageReturn messageReturn) {
        this.mainTableDao = mainTableDao;
        this.messageReturn = messageReturn;
    }

    public void addLabTestMainTable(@RequestParam("testName") String testName){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Optional<LabTestMainTableForMainTestName> mainTestName = mainTableDao.getTestFromMainTableByTestName(testName);
        if (mainTestName.isPresent()) {
            throw new DuplicateResourceException(
                    messages.getString("alreadyExist")
            );
        }
        LabTestMainTableForMainTestName table = new LabTestMainTableForMainTestName();
        table.setTestName(testName);
        mainTableDao.addNewTestToMainTable(table);
    }
}
