package com.azu.hospital.bulding.ward.medicalDevice.dao;

import com.azu.hospital.bulding.ward.medicalDevice.dto.DeviceSumDTO;
import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface WardMedicalDeviceDao {


    List<WardMedicalDevice> saveAllMedicalDevices(List<WardMedicalDevice> wardDevices);

    Page<WardMedicalDevice> getAllWardMedicalDeviceByStatusAndItemName(List<WardInventoryRequestEnum> status ,
                                                                 String itemName ,
                                                                Pageable pageable);
    Optional<WardMedicalDevice> findById(Long id);

    void updateWardMedicalDevice(WardMedicalDevice wardDevice);

    List<DeviceSumDTO> getMedicalDeviceInfo(Long wardId);

    WardMedicalDevice insertWardMedicalDevice(WardMedicalDevice wardDevice);
}
