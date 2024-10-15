package com.azu.hospital.ph.StockMangment.addOtherItems.orders.service;

import com.azu.hospital.exceptions.ResourceNotFoundException;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItemDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.dao.OtherItems;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dao.DeviceExpanseDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.entity.DeviceExpanseTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dao.DeviceExistDao;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.entity.DeviceExistsTable;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.request.DeviceExistRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.request.UpdateDeviceExistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeviceAddServices {

    private final DeviceExistDao deviceExistDao;

    private final OtherItemDao otherItemDao;



    @Autowired
    public DeviceAddServices(
            @Qualifier("otherItemJPA") OtherItemDao otherItemDao,
            @Qualifier("DeviceExistJpa") DeviceExistDao deviceExistDao
    ) {
        this.deviceExistDao = deviceExistDao;
        this.otherItemDao = otherItemDao;
    }


    public void updateExistDevice(
            Long deviceId, UpdateDeviceExistRequest update
    ){

        DeviceExistsTable table = deviceExistDao.getExistDeviceByDeviceId(deviceId)

                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "This Device Doses Not Exist"
                        )
                );

        boolean changes  = false;

        if (update.itemId() != null){
            table.setDeviceDetails(otherItemDao.selectById(update.itemId())
                  .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "This Device Doses Not Exist"
                            )
                    ));
            changes = true;
        }if(update.deviceCount() != null){
            table.setQuantity(update.deviceCount());
            changes = true;
        }if (update.isAvailable() != null){
            table.setIsAvailable(update.isAvailable());
            changes = true;
        }if (update.isDeviceWorking() != null){
            table.setIsDeviceWorking(update.isDeviceWorking());
            changes = true;
        }if (update.type() != null){
            table.setType(update.type());
            changes = true;
        }if (!changes){
            throw new ResourceNotFoundException(
                    "There is no changes"
            );
        }
        deviceExistDao.updateExistsDevice(table);
    }




    public Boolean isItemAvailable(Long itemId) {
        DeviceExistsTable table = deviceExistDao.getExistDeviceByDeviceId(itemId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Item not available"
                        )
                );

        boolean status = true;
        if (table != null) {
            if (table.getQuantity() == 0) {
                status = !table.getIsAvailable();
            }
            ;
        }
        return status;
    }












}
