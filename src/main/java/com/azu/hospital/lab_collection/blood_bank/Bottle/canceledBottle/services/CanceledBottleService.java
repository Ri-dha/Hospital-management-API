package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.services;


import com.azu.hospital.exceptions.BadRequestException;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dao.CanceledBottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.dto.CanceledBottleDtoMapper;
import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.entity.CanceledBottles;
import com.azu.hospital.lab_collection.blood_bank.Bottle.dao.BottleDao;
import com.azu.hospital.lab_collection.blood_bank.Bottle.entity.Bottle;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class CanceledBottleService {

    private final CanceledBottleDao canceledBottleDao;
    private final CanceledBottleDtoMapper mapper;

    private final BottleDao bottleDao;
    private final ExceptionsMessageReturn messageReturn;

    public CanceledBottleService(
            @Qualifier("canceledBottleRepo") CanceledBottleDao canceledBottleDao,
            CanceledBottleDtoMapper mapper,
            @Qualifier("bottleRepo") BottleDao bottleDao, ExceptionsMessageReturn messageReturn
    ) {
        this.canceledBottleDao = canceledBottleDao;
        this.mapper = mapper;
        this.bottleDao = bottleDao;
        this.messageReturn = messageReturn;
    }



    public void cancelBottle(Long bottleId){
//        Locale locale = messageReturn.getMessageLocally();
//        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
//        Bottle bottle = bottleDao.findBottleById(bottleId).orElseThrow(
//                ()-> new ResourceNotFoundException(
//                        messages.getString("resourceNotFound")
//                )
//        );
//
//        if (bottle.getState() != BottleStateEnum.WaittingDoctorAccept){
//            throw new BadRequestException(
//                    messages.getString("cannotAccept")
//            );
//        }
//
//        CanceledBottles canceledBottles = new CanceledBottles(
//                bottle.getBottleType(),
//                bottle.getBloodGroup(),
//                bottle.getBottleNumber(),
//                bottle.getDonatorName(),
//                bottle.getDonatorPhoneNumber(),
//                bottle.getFillDate(),
//                bottle.getExpiredDate(),
//                bottle.getCreatedAt()
//        );
//
//
//        canceledBottles.setBottleOrderInfo(bottle.getBottleOrderInfo());
//
//        canceledBottleDao.createNewCanceledBottles(canceledBottles);
//
//        bottle.setBottleOrderInfo(null);
//        bottle.setState(BottleStateEnum.NoArchive);
//
//        bottleDao.updateBottle(bottle);
    }


}
