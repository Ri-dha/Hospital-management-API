package com.azu.hospital.bulding.ward.medicalDevice.services;

import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.bulding.ward.medicalDevice.dao.WardMedicalDeviceDao;
import com.azu.hospital.bulding.ward.medicalDevice.dto.DeviceSumDTO;
import com.azu.hospital.bulding.ward.medicalDevice.dto.WardMedicalDeviceDto;
import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.bulding.ward.medicalDevice.request.CreateMedicalDeviceDataRequest;
import com.azu.hospital.bulding.ward.medicalDevice.request.MedicalDeviceDataRequest;
import com.azu.hospital.bulding.ward.medicalDevice.dto.WardMedicalDeviceDtoMapper;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WardMedicalDeviceService {


    private final WardMedicalDeviceDao wardMedicalDeviceDao;
    private final WardDao wardDao;
    private final OtherItemDao otherItemDao;

    private final WardMedicalDeviceDtoMapper mapper;

    @Autowired
    public WardMedicalDeviceService(
            @Qualifier("wardMedicalDeviceRepo") WardMedicalDeviceDao wardMedicalDeviceDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            WardMedicalDeviceDtoMapper mapper
    ) {
        this.wardMedicalDeviceDao = wardMedicalDeviceDao;
        this.wardDao = wardDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
    }

    public List<WardMedicalDeviceDto> createNewWardDevice(CreateMedicalDeviceDataRequest request){

        Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                ()->new ResourceNotFoundException("Ward Doesn't Exists")
        );

        List<WardMedicalDevice> devices = new ArrayList<WardMedicalDevice>();

        for (MedicalDeviceDataRequest deviceDataRequest:request.getDevices()){

            OtherItems otherItems = otherItemDao.selectById(deviceDataRequest.getDeviceId()).orElseThrow(
                    ()->new ResourceNotFoundException("Device Doesn't Exists")
            );

            if (otherItems.getItemQuantity() < deviceDataRequest.getCount()){
                throw new ResourceNotFoundException("No Enough " + otherItems.getItemName());
            }

            WardMedicalDevice wardMedicalDevice = new WardMedicalDevice(deviceDataRequest.getCount() ,
                    WardInventoryRequestEnum.Waitting ,
                    deviceDataRequest.getNote());
            wardMedicalDevice.setWard(ward);
            wardMedicalDevice.setDevice(otherItems);
            devices.add(wardMedicalDevice);
        }

        List<WardMedicalDevice> wardMedicalDevice = wardMedicalDeviceDao.saveAllMedicalDevices(devices);

        ward.setWardMedicalDevices(wardMedicalDevice);

        return wardMedicalDevice.stream().map(mapper::toDto).collect(Collectors.toList());

    }


    public List<WardMedicalDeviceDto> getAllWardDeviceByFilter(
            WardInventoryRequestEnum status ,
            String itemName,
            Pageable pageable
    ){

        List<WardInventoryRequestEnum> state = new ArrayList<>();
        if (status == null){
             state = List.of(WardInventoryRequestEnum.values());
        }else {
            state = List.of(status);
            System.out.println(state);
        }

        Page<WardMedicalDevice> wardMedicalDevice =
                wardMedicalDeviceDao.getAllWardMedicalDeviceByStatusAndItemName(state ,
                itemName ,
                pageable);

        return wardMedicalDevice.stream().map(mapper::toDto).collect(Collectors.toList());
    }


    public List<DeviceSumDTO> getMedicalDeviceInfo(Long wardId){

        wardDao.findWardById(wardId).orElseThrow(
                ()->new ResourceNotFoundException("Ward Doesn't Exists")
        );

        return wardMedicalDeviceDao.getMedicalDeviceInfo(wardId);
    }
}
