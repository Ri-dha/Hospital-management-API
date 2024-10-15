package com.azu.hospital.ph.StockMangment.addOtherItems.orders.controller;

import com.azu.hospital.ph.StockMangment.addOtherItems.orders.dto.DeviceExistDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.request.DeviceExistRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.request.UpdateDeviceExistRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.service.DeviceAddServices;
import com.azu.hospital.ph.StockMangment.addOtherItems.orders.service.DeviceExistServices;
import com.azu.hospital.utils.enums.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/devices-exists")
@CrossOrigin
public class DeviceExistController {

    private final DeviceExistServices deviceExistServices;
    private final DeviceAddServices addServices;

    @Autowired
    public DeviceExistController(
             DeviceExistServices deviceExistServices,
            DeviceAddServices addServices
    ) {
        this.deviceExistServices = deviceExistServices;
        this.addServices = addServices;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.OK)
    public void updateDevice(@RequestParam Long deviceId,
                             @ModelAttribute UpdateDeviceExistRequest update
    ){
        addServices.updateExistDevice(deviceId, update);
    }

    @DeleteMapping("/{deviceId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable Long deviceId){
        deviceExistServices.deleteDeviceById(deviceId);
    }

    @GetMapping("/{deviceId}")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceExistDto> getExistDeviceById(@PathVariable Long deviceId){
        return deviceExistServices.getDeviceById(deviceId);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public Page<DeviceExistDto> getAllExistsDevices(
            @RequestParam(value = "type", defaultValue = "", required = false)DeviceType type,
            @RequestParam(value = "isWorking", defaultValue = "true", required = false)Boolean isWorking,
            @PageableDefault Pageable pageable
    ){
        return deviceExistServices.getAllDevices(type, isWorking ,pageable);
    }

    @GetMapping("/device-not-working")
    @PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR', 'INVENTORY_CLERK')")
    public Page<DeviceExistDto> getAllExistsDevicesIsNotWorking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return deviceExistServices.getAllDevicesNotWorking(pageable);
    }
// TODO: 10/17/2023 error

//    @PostMapping("/is-available")
//    @ResponseStatus(HttpStatus.OK)
//    public Boolean isDeviceExist(@RequestParam Long itemId){
//        return addServices.isItemAvailable(itemId);
//    }


}
