package com.azu.hospital.operation.surgical_list.services;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.operation.surgical_list.dao.MainSurgicalListDao;
import com.azu.hospital.operation.surgical_list.entity.MainSurgicalList;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class MainSurgicalListUpdateServices {

    private final MainSurgicalListDao dao;
    private final ExceptionsMessageReturn messageReturn;


    public MainSurgicalListUpdateServices(@Qualifier("MainSurgicalListJpa") MainSurgicalListDao dao, ExceptionsMessageReturn messageReturn) {
        this.dao = dao;
        this.messageReturn = messageReturn;
    }


    public void updateSurgicalNameList(@RequestParam("id") Long id,
                                       @RequestParam("name") String name) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages",locale, new UTF8Control());

        MainSurgicalList list = dao.getSurgicalById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
                );
        boolean changes = false;
        if (name != null){
            list.setSurgicalName(name);
            changes = true;
        }if (!changes){
            throw new ResourceNotFoundException(
                    messages.getString("noChanges")
            );
        }
        dao.updateSurgicalName(list);
    }
}
