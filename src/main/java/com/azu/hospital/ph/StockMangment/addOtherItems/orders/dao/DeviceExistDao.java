package com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao;


import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DeviceExistDao {

    void addNewDevice(DeviceExistsTable request);

    void updateExistsDevice(DeviceExistsTable update);

    void deleteExistsDevice(Long deviceId);

    Page<DeviceExistsTable> getAllExistsDevices(DeviceType type , Boolean isWorking,Pageable pageable);
    List<DeviceExistsTable> getAllExistsDevicesIsNotWorking(Boolean isDeviceIsWorking, Pageable pageable);

    Optional<DeviceExistsTable> getExistDeviceByDeviceId(Long deviceId);

    Optional<DeviceExistsTable> getExistDeviceById(Long deviceId);



}
