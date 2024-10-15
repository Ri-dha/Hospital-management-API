package com.azu.hospital.bulding.ward.medicalDevice.dao;

import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.bulding.ward.medicalDevice.dto.DeviceSumDTO;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardMedicalDeviceRepository extends JpaRepository<WardMedicalDevice, Long> {

    Page<WardMedicalDevice> getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(List<WardInventoryRequestEnum> status,
                                                                                                   String device_itemName, Pageable pageable);


    @Query("SELECT NEW com.azu.hospital.bulding.ward.medicalDevice.dto.DeviceSumDTO(wd.device.itemName, SUM(wd.count)" +
            ") FROM WardMedicalDevice wd WHERE wd.ward.wardId = :ward_wardId GROUP BY wd.device.itemName")
    List<DeviceSumDTO> getAllByWardWardId(@Param("ward_wardId") Long ward_wardId);


}


