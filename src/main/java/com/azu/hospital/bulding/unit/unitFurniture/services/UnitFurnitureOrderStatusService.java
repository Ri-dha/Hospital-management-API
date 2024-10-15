package com.azu.hospital.bulding.unit.unitFurniture.services;

import com.azu.hospital.bulding.unit.unitFurniture.dao.UnitFurnitureDao;
import com.azu.hospital.bulding.unit.unitFurniture.dto.UnitFurnitureDtoMapper;
import com.azu.hospital.bulding.unit.unitFurniture.entity.UnitFurniture;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class UnitFurnitureOrderStatusService {

    private final UnitFurnitureDao unitFurnitureDao;

    private final OtherItemDao otherItemDao;

    private final UnitFurnitureDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;



    public UnitFurnitureOrderStatusService(UnitFurnitureDao unitFurnitureDao, OtherItemDao otherItemDao, UnitFurnitureDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.unitFurnitureDao = unitFurnitureDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public void acceptOrder(Long id , Boolean isAccept , String receivedNote){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        UnitFurniture unitFurniture = unitFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitFurniture.getStatus() != UnitInventoryRequestEnum.Waitting){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        if (!isAccept) {
            if (receivedNote == null || receivedNote.isBlank()) {
                throw new ResourceNotFoundException(
                        messages.getString("fieldRequired")
                );
            }
            unitFurniture.setReceivedNote(receivedNote);
        }

        unitFurniture.setStatus(isAccept ? UnitInventoryRequestEnum.Accepted :UnitInventoryRequestEnum.Rejected);

        unitFurnitureDao.updateUnitFurniture(unitFurniture);
    }


    public void prepareAndPending(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        UnitFurniture unitFurniture = unitFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitFurniture.getStatus() != UnitInventoryRequestEnum.Accepted && unitFurniture.getStatus() != UnitInventoryRequestEnum.Preparing){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        unitFurniture.setStatus(unitFurniture.getStatus() == UnitInventoryRequestEnum.Accepted ?
                UnitInventoryRequestEnum.Preparing : UnitInventoryRequestEnum.Pending);

        unitFurnitureDao.updateUnitFurniture(unitFurniture);
    }

    public void receivedNoReceived(Long id , Boolean isReceived){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        UnitFurniture unitFurniture = unitFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitFurniture.getStatus() != UnitInventoryRequestEnum.Pending){
            throw new ResourceNotFoundException(
                    messages.getString("cannotAccept")
            );
        }

        unitFurniture.setStatus(isReceived ? UnitInventoryRequestEnum.Received :UnitInventoryRequestEnum.Not_Received);

        unitFurnitureDao.updateUnitFurniture(unitFurniture);
    }
}
