package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao;


import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeviceExpanseDao {

    void addNewDeviceExpanseTable(DeviceExpanseTable request);

    void updateDeviceExpanseTable(DeviceExpanseTable update);

    void deleteDeviceExpanseTable(Long deviceId);

    Page<DeviceExpanseTable> getAllExistsDevices(Pageable pageable);

    Optional<DeviceExpanseTable> getExpanseItemByItemId(Long deviceId);

}
