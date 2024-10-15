package com.azu.hospital.lab_collection.blood_bank.Bottle.services;

import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.dao.BottleOrderDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class BottleOrderServiceState {

    private final BottleDao bottleDao;

    private final BottleOrderDao bottleOrderDao;

    private final ExceptionsMessageReturn messageReturn;


    @Autowired
    public BottleOrderServiceState(
            @Qualifier("bottleRepo") BottleDao bottleDao,
            @Qualifier("bottleOrderRepo") BottleOrderDao bottleOrderDao,
             ExceptionsMessageReturn messageReturn
    ) {
        this.bottleDao = bottleDao;
        this.bottleOrderDao = bottleOrderDao;
        this.messageReturn = messageReturn;
    }


    public void prepareBottle(Long bottleId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(
                () -> new ResourceNotFoundException(
                        messages.getString("bloodBottleNotFound") + " " + bottleId
                )
        );

        if (bottle.getState() != BottleStateEnum.WaittingDoctorAccept) {
            throw new BadRequestException(
                    messages.getString("cannotAccept") + " " + bottleId
            );
        }

        bottle.setState(BottleStateEnum.Preparing);

        bottleDao.updateBottle(bottle);

    }


    public void completePrepareBottle(Long bottleId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(() -> new ResolutionException("Bottle Doesn't Exists"));

        if (bottle.getState() != BottleStateEnum.Preparing) {
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        bottle.setState(BottleStateEnum.Ready);

        bottleDao.updateBottle(bottle);

    }

    public void receivedBottle(Long bottleId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(() -> new ResolutionException("Bottle Doesn't Exists"));

        if (bottle.getState() != BottleStateEnum.Ready) {
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        bottle.setState(BottleStateEnum.Received);

        bottleDao.updateBottle(bottle);

    }

    public void notReceivedBottle(Long bottleId) {
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(() -> new ResolutionException(
                messages.getString("bloodBottleNotFound") + " " + bottleId
        ));

        if (bottle.getState() != BottleStateEnum.Ready) {
            throw new BadRequestException(
                    messages.getString("cannotAccept")
            );
        }

        bottle.setState(BottleStateEnum.NotReceived);

        bottleDao.updateBottle(bottle);

    }




}
