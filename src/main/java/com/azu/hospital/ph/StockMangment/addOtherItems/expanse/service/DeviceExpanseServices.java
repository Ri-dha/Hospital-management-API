
package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dto.DeviceExpanseDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dto.DeviceExpanseDtoMapper;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("DeviceExitsServices")
public class DeviceExpanseServices {

    private final DeviceExpanseDao deviceExpanseDao;
    private final DeviceExpanseDtoMapper deviceExpanseDtoMapper;

    public DeviceExpanseServices(DeviceExpanseDao deviceExpanseDao, DeviceExpanseDtoMapper deviceExpanseDtoMapper) {
        this.deviceExpanseDao = deviceExpanseDao;
        this.deviceExpanseDtoMapper = deviceExpanseDtoMapper;
    }



    public List<DeviceExpanseDto> getDeviceById(Long deviceId) {
        DeviceExpanseTable device = deviceExpanseDao.getExpanseItemByItemId(deviceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );
        return deviceExpanseDao.getExpanseItemByItemId(deviceId)
                .stream()
                .map(deviceExpanseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Page<DeviceExpanseDto> getAllDevices(Pageable pageable){
        Page<DeviceExpanseTable> page = deviceExpanseDao.getAllExistsDevices(pageable);
        List<DeviceExpanseDto> deviceExistDtoList = page
                .stream()
                .map(deviceExpanseDtoMapper::mapToDto)
                .toList();
        return new PageImpl<>(deviceExistDtoList, pageable, page.getTotalElements());
    }


    public void deleteDeviceById(Long deviceId){
        DeviceExpanseTable device = deviceExpanseDao.getExpanseItemByItemId(deviceId)
               .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );
        deviceExpanseDao.deleteDeviceExpanseTable(deviceId);
    }
}