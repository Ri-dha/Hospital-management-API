package com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao;


import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("DeviceExistJpa")
public class DeviceExistJpaDataAccess implements DeviceExistDao {

    private final DeviceExistRepository deviceExistRepository;

    public DeviceExistJpaDataAccess(DeviceExistRepository deviceExistRepository) {
        this.deviceExistRepository = deviceExistRepository;
    }

    @Override
    public void addNewDevice(DeviceExistsTable request) {
        deviceExistRepository.save(request);
    }

    @Override
    public void updateExistsDevice(DeviceExistsTable update) {
        deviceExistRepository.save(update);
    }

    @Override
    public void deleteExistsDevice(Long deviceId) {
        deviceExistRepository.deleteById(deviceId);
    }

    @Override
    public Page<DeviceExistsTable> getAllExistsDevices(DeviceType type , Boolean isWorking , Pageable pageable) {
        return deviceExistRepository.findAllByType(type, isWorking , pageable);
    }

    @Override
    public List<DeviceExistsTable> getAllExistsDevicesIsNotWorking(Boolean isDeviceIsWorking, Pageable pageable) {
        return deviceExistRepository.findAllByIsDeviceWorking(isDeviceIsWorking, pageable);
    }

    @Override
    public Optional<DeviceExistsTable> getExistDeviceByDeviceId(Long deviceId) {
        return deviceExistRepository.findAllByDeviceId(deviceId);
    }

    @Override
    public Optional<DeviceExistsTable> getExistDeviceById(Long deviceId) {
        return deviceExistRepository.findById(deviceId);
    }


}
