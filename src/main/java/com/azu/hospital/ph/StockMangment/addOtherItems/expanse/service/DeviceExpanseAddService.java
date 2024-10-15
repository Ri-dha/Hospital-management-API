package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.request.UpdateDeviceExpanseRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("DevicesExpanseAddService")
public class DeviceExpanseAddService {

    private final DeviceExpanseDao deviceExpanseDao;
    private final OtherItemDao otherItemDao;

    @Autowired
    public DeviceExpanseAddService(@Qualifier("DeviceExpanseJpa") DeviceExpanseDao deviceExpanseDao,
                                    OtherItemDao otherItemDao
    ) {
        this.deviceExpanseDao = deviceExpanseDao;
        this.otherItemDao = otherItemDao;
    }


    public void updateExpanseDevice(
            Long deviceId, UpdateDeviceExpanseRequest update
    ) {
        DeviceExpanseTable table = deviceExpanseDao.getExpanseItemByItemId(deviceId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );


        boolean changes = false;

        if (update.itemId() != null) {
            table.getDeviceExistsTable().setDeviceDetails(otherItemDao.selectById(update.itemId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "This Device Doses Not Exist"
                            )
                    ));
            changes = true;
        }
        if (update.deviceCount() != null) {
            table.setQuantity(update.deviceCount());
            changes = true;
        }
        if (update.devicePlace() != null) {
            table.setDevicePlace(update.devicePlace());
            changes = true;
        }
        if (!changes) {
            throw new ResourceNotFoundException(
                    "There is no changes"
            );
        }
        deviceExpanseDao.updateDeviceExpanseTable(table);
    }
}