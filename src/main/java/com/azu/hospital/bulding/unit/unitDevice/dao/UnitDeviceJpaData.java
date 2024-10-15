package com.azu.hospital.bulding.unit.unitDevice.dao;


import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("unitDeviceRepo")
public class UnitDeviceJpaData implements UnitDeviceDao {

    private final UnitDeviceRepository unitDeviceRepository;

    public UnitDeviceJpaData(UnitDeviceRepository unitDeviceRepository) {
        this.unitDeviceRepository = unitDeviceRepository;
    }

    @Override
    public Page<UnitDevice> getAllUnitDeviceByStatusAndItemName(List<UnitInventoryRequestEnum> status, String itemName ,
                                                                Pageable pageable) {
        return unitDeviceRepository.getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(status , itemName, pageable);
    }

    @Override
    public List<UnitDevice> saveAllDevices(List<UnitDevice> unitDevices) {
        return unitDeviceRepository.saveAll(unitDevices);
    }

    @Override
    public Optional<UnitDevice> findById(Long id) {
        return unitDeviceRepository.findById(id);
    }

    @Override
    public void updateUnitDevice(UnitDevice unitDevice) {
        unitDeviceRepository.save(unitDevice);
    }

    @Override
    public UnitDevice insertUnitDevice(UnitDevice unitDevice) {
        return unitDeviceRepository.save(unitDevice);
    }

}
