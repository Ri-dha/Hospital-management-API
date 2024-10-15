package com.azu.hospital.bulding.ward.wardDevice.dao;

import com.azu.hospital.bulding.ward.wardDevice.entity.WardDevice;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WardDeviceRepository extends JpaRepository<WardDevice, Long> {

    Page<WardDevice> getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(List<WardInventoryRequestEnum> status,
                                                                                        String device_itemName, Pageable pageable);
}
