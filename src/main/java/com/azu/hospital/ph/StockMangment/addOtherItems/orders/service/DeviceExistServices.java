package com.azu.hospital.ph.StockMangment.addOtherItems.orders.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dto.DeviceExistDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dto.DeviceExistDtoMapper;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceExistServices {

    private final DeviceExistDao existDao;
    private final DeviceExistDtoMapper deviceExistDtoMapper;


    public DeviceExistServices(
            @Qualifier("DeviceExistJpa") DeviceExistDao existDao,
            DeviceExistDtoMapper deviceExistDtoMapper
    ) {
        this.existDao = existDao;
        this.deviceExistDtoMapper = deviceExistDtoMapper;
    }

    public List<DeviceExistDto> getDeviceById(Long deviceId) {


        DeviceExistsTable device = existDao.getExistDeviceByDeviceId(deviceId)

                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );
        return existDao.getExistDeviceByDeviceId(device.getDeviceId())
                .stream()
                .map(deviceExistDtoMapper)
                .collect(Collectors.toList());
    }

    public Page<DeviceExistDto> getAllDevices(DeviceType type ,  Boolean isWorking , Pageable pageable){

        Page<DeviceExistsTable> data = existDao.getAllExistsDevices(type , isWorking ,pageable);

        return data.map(deviceExistDtoMapper);
    }

    
    public Page<DeviceExistDto> getAllDevicesNotWorking (Pageable pageable){
        List<DeviceExistsTable> page = existDao.getAllExistsDevicesIsNotWorking(false,pageable);
     List<DeviceExistDto> deviceExistDtoList = page
                .stream()
                .map(deviceExistDtoMapper)
                .toList();
        return new PageImpl<>(deviceExistDtoList, pageable, pageable.getPageSize());
    }


    public void deleteDeviceById(Long deviceId){

        DeviceExistsTable device = existDao.getExistDeviceByDeviceId(deviceId)
               .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );
        existDao.deleteExistsDevice(device.getDeviceId());
    }



}
