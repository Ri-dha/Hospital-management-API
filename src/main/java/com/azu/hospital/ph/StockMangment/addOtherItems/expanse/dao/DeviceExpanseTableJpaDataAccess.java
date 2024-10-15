package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao;


import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository("DeviceExpanseJpa")
public class DeviceExpanseTableJpaDataAccess implements DeviceExpanseDao {


    private final DeviceExpanseTableRepository deviceExpanseRepository;

    public DeviceExpanseTableJpaDataAccess(DeviceExpanseTableRepository deviceExpanseRepository) {
        this.deviceExpanseRepository = deviceExpanseRepository;
    }

    @Override
    public void addNewDeviceExpanseTable(DeviceExpanseTable request) {
        deviceExpanseRepository.save(request);
    }

    @Override
    public void updateDeviceExpanseTable(DeviceExpanseTable update) {
        deviceExpanseRepository.save(update);
    }

    @Override
    public void deleteDeviceExpanseTable(Long deviceId) {
        deviceExpanseRepository.deleteById(deviceId);
    }

    @Override
    public Page<DeviceExpanseTable> getAllExistsDevices(Pageable pageable) {
        return deviceExpanseRepository.findAll(pageable);
    }

    @Override
    public Optional<DeviceExpanseTable> getExpanseItemByItemId(Long deviceId) {
        return deviceExpanseRepository.findAllByDeviceExistsTable_DeviceDetails_ItemId(deviceId);
    }


}
