package com.azu.hospital.bulding.ward.medicalDevice.dao;


import com.azu.hospital.bulding.ward.medicalDevice.dto.DeviceSumDTO;
import com.azu.hospital.bulding.ward.medicalDevice.entity.WardMedicalDevice;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("wardMedicalDeviceRepo")
public class WardMedicalDeviceJpaData implements WardMedicalDeviceDao {

    private final WardMedicalDeviceRepository wardMedicalDeviceRepository;

    public WardMedicalDeviceJpaData(WardMedicalDeviceRepository wardMedicalDeviceRepository) {
        this.wardMedicalDeviceRepository = wardMedicalDeviceRepository;
    }

    @Override
    public Page<WardMedicalDevice> getAllWardMedicalDeviceByStatusAndItemName(List<WardInventoryRequestEnum> status, String itemName ,
                                                                       Pageable pageable) {
        return wardMedicalDeviceRepository.getAllByStatusInAndDeviceItemNameContainingIgnoreCaseOrderByStatusDesc(status ,
                itemName,
                pageable);
    }

    @Override
    public List<WardMedicalDevice> saveAllMedicalDevices(List<WardMedicalDevice> wardDevices) {
        return wardMedicalDeviceRepository.saveAll(wardDevices);
    }

    @Override
    public Optional<WardMedicalDevice> findById(Long id) {
        return wardMedicalDeviceRepository.findById(id);
    }

    @Override
    public void updateWardMedicalDevice(WardMedicalDevice wardDevice) {
        wardMedicalDeviceRepository.save(wardDevice);
    }

    @Override
    public List<DeviceSumDTO> getMedicalDeviceInfo(Long wardId) {
        return wardMedicalDeviceRepository.getAllByWardWardId(wardId);
    }

    @Override
    public WardMedicalDevice insertWardMedicalDevice(WardMedicalDevice wardDevice) {
        return null;
    }


}
