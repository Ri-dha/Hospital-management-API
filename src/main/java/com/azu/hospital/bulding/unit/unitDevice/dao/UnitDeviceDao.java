package com.azu.hospital.bulding.unit.unitDevice.dao;

import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UnitDeviceDao {


    List<UnitDevice> saveAllDevices(List<UnitDevice> unitDevices);

    Page<UnitDevice> getAllUnitDeviceByStatusAndItemName(List<UnitInventoryRequestEnum> status , String itemName ,
                                                         Pageable pageable);
    Optional<UnitDevice> findById(Long id);

    void updateUnitDevice(UnitDevice unitDevice);

    UnitDevice insertUnitDevice(UnitDevice unitDevice);
}
