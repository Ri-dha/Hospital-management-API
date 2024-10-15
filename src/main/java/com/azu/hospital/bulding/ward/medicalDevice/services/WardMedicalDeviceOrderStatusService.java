package com.azu.hospital.bulding.ward.medicalDevice.services;

import com.azu.hospital.bulding.ward.medicalDevice.dao.WardMedicalDeviceDao;
import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.bulding.ward.medicalDevice.dto.WardMedicalDeviceDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.stereotype.Service;

@Service
public class WardMedicalDeviceOrderStatusService {

    private final WardMedicalDeviceDao wardMedicalDeviceDao;

    private final OtherItemDao otherItemDao;

    private final WardMedicalDeviceDtoMapper mapper;

    public WardMedicalDeviceOrderStatusService(WardMedicalDeviceDao wardMedicalDeviceDao, OtherItemDao otherItemDao,
                                               WardMedicalDeviceDtoMapper mapper) {
        this.wardMedicalDeviceDao = wardMedicalDeviceDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
    }


    public void acceptOrder(Long id , Boolean isAccept , String receivedNote){
        WardMedicalDevice wardMedicalDevice = wardMedicalDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Ward Device Doesn't Exists")
        );

        if (wardMedicalDevice.getStatus() != WardInventoryRequestEnum.Waitting){
            throw new ResourceNotFoundException("Order Is Already Accepted Or Rejected");
        }

        if (!isAccept) {
            if (receivedNote == null || receivedNote.isBlank()) {
                throw new ResourceNotFoundException("receivedNote Must Not Blank");
            }
            wardMedicalDevice.setReceivedNote(receivedNote);
        }

        wardMedicalDevice.setStatus(isAccept ? WardInventoryRequestEnum.Accepted :WardInventoryRequestEnum.Rejected);

        wardMedicalDeviceDao.updateWardMedicalDevice(wardMedicalDevice);
    }


    public void prepareAndPending(Long id){
        WardMedicalDevice wardMedicalDevice = wardMedicalDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Ward Device Doesn't Exists")
        );

        if (wardMedicalDevice.getStatus() != WardInventoryRequestEnum.Accepted && wardMedicalDevice.getStatus() != WardInventoryRequestEnum.Preparing){
            throw new ResourceNotFoundException("Order Is Already Preparing Or Pending");
        }

        wardMedicalDevice.setStatus(wardMedicalDevice.getStatus() == WardInventoryRequestEnum.Accepted ?
                WardInventoryRequestEnum.Preparing : WardInventoryRequestEnum.Pending);

        wardMedicalDeviceDao.updateWardMedicalDevice(wardMedicalDevice);
    }

    public void receivedNoReceived(Long id , Boolean isReceived){
        WardMedicalDevice wardMedicalDevice = wardMedicalDeviceDao.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Ward Device Doesn't Exists")
        );

        if (wardMedicalDevice.getStatus() != WardInventoryRequestEnum.Pending){
            throw new ResourceNotFoundException("Order Must Be Pending");
        }

        wardMedicalDevice.setStatus(isReceived ? WardInventoryRequestEnum.Received :
                WardInventoryRequestEnum.Not_Received);

        wardMedicalDeviceDao.updateWardMedicalDevice(wardMedicalDevice);
    }
}
