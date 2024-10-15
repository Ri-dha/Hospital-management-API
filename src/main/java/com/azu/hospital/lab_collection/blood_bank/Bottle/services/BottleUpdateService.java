package com.azu.hospital.lab_collection.blood_bank.Bottle.services;


import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.lab_collection.blood_bank.Bottle.request.CreateNewBottleRequest;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BottleUpdateService {
    private final BottleDao bottleDao;
    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public BottleUpdateService(BottleDao bottleDao, ExceptionsMessageReturn messageReturn) {
        this.bottleDao = bottleDao;
        this.messageReturn = messageReturn;
    }


    public void updateBottleState(Long bottleId, CreateNewBottleRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bloodBottleNotFound") + " " + bottleId
                )
        );

        boolean changes = false;

        if(request.getBloodGroup() != null){
            bottle.setBloodGroup(request.getBloodGroup());
            changes = true;
        }
        if(request.getBottleType() != null){
            bottle.setBottleType(request.getBottleType());
            changes = true;
        }
        if(request.getDonatorName() != null){
            bottle.setDonatorName(request.getDonatorName());
            changes = true;
        }
        if(request.getBottleNumber() != null){
            bottle.setBottleNumber(request.getBottleNumber());
            changes = true;
        }
        if(request.getDonatorPhoneNumber() != null){
            bottle.setDonatorPhoneNumber(request.getDonatorPhoneNumber());
            changes = true;
        }
        if(request.getFillDate() != null){
            bottle.setFillDate(request.getFillDate());
            changes = true;
        }
        if(request.getExpiredDate() != null){
            bottle.setExpiredDate(request.getExpiredDate());
            changes = true;
        }
        if(!changes){
            throw new BadRequestException(
                    messages.getString("noChanges")
            );
        }
        bottleDao.updateBottle(bottle);

    }


}
