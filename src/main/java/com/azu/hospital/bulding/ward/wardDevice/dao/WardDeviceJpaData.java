package com.azu.hospital.bulding.ward.wardDevice.dao;


import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("wardDeviceRepo")
public class WardDeviceJpaData implements WardDeviceDao{

    private final WardDeviceRepository wardDeviceRepository;

    public WardDeviceJpaData(WardDeviceRepository wardDeviceRepository) {
        this.wardDeviceRepository = wardDeviceRepository;
    }

    @Override
    public Page<WardDevice> getAllWardDeviceByStatusAndItemName(List<WardInventoryRequestEnum> status, String itemName ,
                                                                Pageable pageable) {
        return wardDeviceRepository.getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(status , itemName, pageable);
    }

    @Override
    public List<WardDevice> saveAllDevices(List<WardDevice> wardDevices) {
        return wardDeviceRepository.saveAll(wardDevices);
    }

    @Override
    public Optional<WardDevice> findById(Long id) {
        return wardDeviceRepository.findById(id);
    }

    @Override
    public void updateWardDevice(WardDevice wardDevice) {
        wardDeviceRepository.save(wardDevice);
    }

    @Override
    public WardDevice insertWardDevice(WardDevice wardDevice) {
        return wardDeviceRepository.save(wardDevice);
    }

}
