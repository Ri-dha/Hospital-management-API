package com.azu.hospital.bulding.ward.wardDevice.services;

import com.azu.hospital.bulding.ward.wardDevice.dao.WardDeviceDao;
import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDtoMapper;
import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class WardDeviceOrderStatusService {

    private final WardDeviceDao wardDeviceDao;

    private final OtherItemDao otherItemDao;

    private final WardDeviceDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;


    public WardDeviceOrderStatusService(WardDeviceDao wardDeviceDao, OtherItemDao otherItemDao, WardDeviceDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.wardDeviceDao = wardDeviceDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public void acceptOrder(Long id , Boolean isAccept , String receivedNote){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardDevice wardDevice = wardDeviceDao.findById(id)
                .orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (wardDevice.getStatus() != WardInventoryRequestEnum.Waitting){
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
            wardDevice.setReceivedNote(receivedNote);
        }

        wardDevice.setStatus(isAccept ? WardInventoryRequestEnum.Accepted :WardInventoryRequestEnum.Rejected);

        wardDeviceDao.updateWardDevice(wardDevice);
    }


    public void prepareAndPending(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardDevice wardDevice = wardDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (wardDevice.getStatus() != WardInventoryRequestEnum.Accepted && wardDevice.getStatus() != WardInventoryRequestEnum.Preparing){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        wardDevice.setStatus(wardDevice.getStatus() == WardInventoryRequestEnum.Accepted ?
                WardInventoryRequestEnum.Preparing : WardInventoryRequestEnum.Pending);

        wardDeviceDao.updateWardDevice(wardDevice);
    }

    public void receivedNoReceived(Long id , Boolean isReceived){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        WardDevice wardDevice = wardDeviceDao.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException(
                                messages.getString("resourceNotFound")
                        )
        );

        if (wardDevice.getStatus() != WardInventoryRequestEnum.Pending){
            throw new ResourceNotFoundException(
                    messages.getString("cannotAccept")
            );
        }

        wardDevice.setStatus(isReceived ? WardInventoryRequestEnum.Received :WardInventoryRequestEnum.Not_Received);

        wardDeviceDao.updateWardDevice(wardDevice);
    }


}
