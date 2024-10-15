package com.azu.hospital.bulding.ward.wardFurniture.services;

import com.azu.hospital.bulding.ward.wardFurniture.dao.WardFurnitureDao;
import com.azu.hospital.bulding.ward.wardFurniture.dto.WardFurnitureDtoMapper;
import com.azu.hospital.bulding.ward.wardFurniture.entity.WardFurniture;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class WardFurnitureOrderStatusService {

    private final WardFurnitureDao wardFurnitureDao;

    private final OtherItemDao otherItemDao;

    private final WardFurnitureDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public WardFurnitureOrderStatusService(WardFurnitureDao wardFurnitureDao, OtherItemDao otherItemDao, WardFurnitureDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.wardFurnitureDao = wardFurnitureDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public void acceptOrder(Long id , Boolean isAccept , String receivedNote){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardFurniture wardFurniture = wardFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (wardFurniture.getStatus() != WardInventoryRequestEnum.Waitting){
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
            wardFurniture.setReceivedNote(receivedNote);
        }
        wardFurniture.setStatus(isAccept ? WardInventoryRequestEnum.Accepted :WardInventoryRequestEnum.Rejected);

        wardFurnitureDao.updateWardFurniture(wardFurniture);
    }


    public void prepareAndPending(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardFurniture wardFurniture = wardFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (wardFurniture.getStatus() != WardInventoryRequestEnum.Accepted && wardFurniture.getStatus() != WardInventoryRequestEnum.Preparing){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        wardFurniture.setStatus(wardFurniture.getStatus() == WardInventoryRequestEnum.Accepted ?
                WardInventoryRequestEnum.Preparing : WardInventoryRequestEnum.Pending);

        wardFurnitureDao.updateWardFurniture(wardFurniture);
    }

    public void receivedNoReceived(Long id , Boolean isReceived){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardFurniture wardFurniture = wardFurnitureDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (wardFurniture.getStatus() != WardInventoryRequestEnum.Pending){
            throw new ResourceNotFoundException(
                    messages.getString("cannotAccept")
            );
        }

        wardFurniture.setStatus(isReceived ? WardInventoryRequestEnum.Received :WardInventoryRequestEnum.Not_Received);

        wardFurnitureDao.updateWardFurniture(wardFurniture);
    }
}
