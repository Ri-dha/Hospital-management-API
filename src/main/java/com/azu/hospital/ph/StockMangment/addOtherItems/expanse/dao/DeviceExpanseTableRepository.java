package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao;


import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface DeviceExpanseTableRepository extends JpaRepository<DeviceExpanseTable, Long> {

    Optional<DeviceExpanseTable> findAllByDeviceExistsTable_DeviceDetails_ItemId(Long deviceId);

}
