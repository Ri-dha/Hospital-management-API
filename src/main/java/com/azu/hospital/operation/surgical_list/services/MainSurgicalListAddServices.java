package com.azu.hospital.operation.surgical_list.services;

import com.azu.hospital.exceptions.DuplicateResourceException;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSurgicalListAddServices {
    private final MainSurgicalListDao mainSurgicalListDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public MainSurgicalListAddServices(@Qualifier("MainSurgicalListJpa") MainSurgicalListDao mainSurgicalListDao, ExceptionsMessageReturn messageReturn) {
        this.mainSurgicalListDao = mainSurgicalListDao;
        this.messageReturn = messageReturn;
    }

    public void addSurgicalName(@RequestParam("name") String name) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());
        boolean names = mainSurgicalListDao.existsAllBySurgicalName(name);
               if (names){
                   throw  new DuplicateResourceException(
                            messages.getString("alreadyExist")
                   );
               }
        MainSurgicalList mainSurgicalList = new MainSurgicalList(
                name
        );

        mainSurgicalListDao.addSurgicalName(mainSurgicalList);
    }


}
