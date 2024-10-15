package com.azu.hospital.bulding.unit.unitDevice.dao;

import com.azu.hospital.bulding.unit.unitDevice.entity.UnitDevice;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitDeviceRepository extends JpaRepository<UnitDevice, Long> {

    Page<UnitDevice> getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(List<UnitInventoryRequestEnum> status,
                                                                                            String device_itemName, Pageable pageable);
}
