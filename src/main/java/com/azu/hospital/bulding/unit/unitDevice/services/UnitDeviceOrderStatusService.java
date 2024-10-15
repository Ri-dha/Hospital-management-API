package com.azu.hospital.bulding.unit.unitDevice.services;

import com.azu.hospital.bulding.unit.unitDevice.dao.UnitDeviceDao;
import com.azu.hospital.bulding.unit.unitDevice.dto.UnitDeviceDtoMapper;
import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class UnitDeviceOrderStatusService {

    private final UnitDeviceDao unitDeviceDao;

    private final OtherItemDao otherItemDao;

    private final UnitDeviceDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;



    public UnitDeviceOrderStatusService(UnitDeviceDao unitDeviceDao, OtherItemDao otherItemDao, UnitDeviceDtoMapper mapper, ExceptionsMessageReturn messageReturn) {
        this.unitDeviceDao = unitDeviceDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }


    public void acceptOrder(Long id , Boolean isAccept , String receivedNote){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        UnitDevice unitDevice = unitDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitDevice.getStatus() != UnitInventoryRequestEnum.Waitting){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        if (!isAccept) {
            if (receivedNote == null || receivedNote.isBlank()) {
                throw new ResourceNotFoundException(
                        messages.getString("isRequired")
                );
            }
            unitDevice.setReceivedNote(receivedNote);
        }

        unitDevice.setStatus(isAccept ? UnitInventoryRequestEnum.Accepted :UnitInventoryRequestEnum.Rejected);

        unitDeviceDao.updateUnitDevice(unitDevice);
    }


    public void prepareAndPending(Long id){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        UnitDevice unitDevice = unitDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitDevice.getStatus() != UnitInventoryRequestEnum.Accepted && unitDevice.getStatus() != UnitInventoryRequestEnum.Preparing){
            throw new ResourceNotFoundException(
                    messages.getString("alreadyExist")
            );
        }

        unitDevice.setStatus(unitDevice.getStatus() == UnitInventoryRequestEnum.Accepted ?
                UnitInventoryRequestEnum.Preparing : UnitInventoryRequestEnum.Pending);

        unitDeviceDao.updateUnitDevice(unitDevice);
    }

    public void receivedNoReceived(Long id , Boolean isReceived){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());
        UnitDevice unitDevice = unitDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        if (unitDevice.getStatus() != UnitInventoryRequestEnum.Pending){
            throw new ResourceNotFoundException(
                    messages.getString("cannotAccept")
            );
        }

        unitDevice.setStatus(isReceived ? UnitInventoryRequestEnum.Received :UnitInventoryRequestEnum.Not_Received);

        unitDeviceDao.updateUnitDevice(unitDevice);
    }
}
