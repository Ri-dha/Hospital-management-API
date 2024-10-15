package com.azu.hospital.bulding.ward.wardDevice.dao;

import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WardDeviceDao {


    List<WardDevice> saveAllDevices(List<WardDevice> wardDevices);

    Page<WardDevice> getAllWardDeviceByStatusAndItemName(List<WardInventoryRequestEnum> status , String itemName ,
                                                         Pageable pageable);
    Optional<WardDevice> findById(Long id);

    void updateWardDevice(WardDevice wardDevice);

    WardDevice insertWardDevice(WardDevice wardDevice);
}
