package com.azu.hospital.bulding.ward.wardDevice.services;

import com.azu.hospital.bulding.ward.wardDevice.dao.WardDeviceDao;
import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDto;
import com.azu.hospital.bulding.ward.wardDevice.dto.WardDeviceDtoMapper;
import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.bulding.ward.wardDevice.request.CreateDeviceDataRequest;
import com.azu.hospital.bulding.ward.wardDevice.request.DeviceDataRequest;
import com.azu.hospital.bulding.ward.dao.WardDao;
import com.azu.hospital.bulding.ward.entity.Ward;
import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.transulator.ExceptionsMessageReturn;
import com.azu.hospital.transulator.UTF8Control;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class WardDeviceService {


    private final WardDeviceDao wardDeviceDao;
    private final WardDao wardDao;
    private final OtherItemDao otherItemDao;

    private final WardDeviceDtoMapper mapper;
    private final ExceptionsMessageReturn messageReturn;

    @Autowired
    public WardDeviceService(
            @Qualifier("wardDeviceRepo") WardDeviceDao wardDeviceDao,
            @Qualifier("wardRepo") WardDao wardDao,
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            WardDeviceDtoMapper mapper, ExceptionsMessageReturn messageReturn
    ) {
        this.wardDeviceDao = wardDeviceDao;
        this.wardDao = wardDao;
        this.otherItemDao = otherItemDao;
        this.mapper = mapper;
        this.messageReturn = messageReturn;
    }

    public List<WardDeviceDto> createNewWardDevice(CreateDeviceDataRequest request){
        Locale locale = messageReturn.getMessageLocally();
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale, new UTF8Control());

        Ward ward = wardDao.findWardById(request.getWardId()).orElseThrow(
                ()->new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                )
        );

        List<WardDevice> devices = new ArrayList<WardDevice>();

        for (DeviceDataRequest deviceDataRequest:request.getDevices()){

            OtherItems otherItems = otherItemDao.selectById(deviceDataRequest.getDeviceId()).orElseThrow(
                    ()->new ResourceNotFoundException(
                            messages.getString("resourceNotFound")
                    )
            );

            if (otherItems.getItemQuantity() < deviceDataRequest.getCount()){
                throw new ResourceNotFoundException(
                        messages.getString("resourceNotFound")
                );
            }

            WardDevice wardDevice = new WardDevice(deviceDataRequest.getCount() , WardInventoryRequestEnum.Waitting ,
                    deviceDataRequest.getNote());
            wardDevice.setWard(ward);
            wardDevice.setDevice(otherItems);
            devices.add(wardDevice);
        }

        List<WardDevice> wardDevices = wardDeviceDao.saveAllDevices(devices);

        ward.setWardDevices(wardDevices);

        return wardDevices
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }


    public List<WardDeviceDto> getAllWardDeviceByFilter(
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

        Page<WardDevice> wardDevice = wardDeviceDao.getAllWardDeviceByStatusAndItemName(state , itemName , pageable);

        return wardDevice.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
