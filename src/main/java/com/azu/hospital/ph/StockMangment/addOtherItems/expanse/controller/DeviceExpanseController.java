package com.azu.hospital.ph.StockMangment.addOtherItems.expanse.controller;

import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.dto.DeviceExpanseDto;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.request.UpdateDeviceExpanseRequest;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.service.DeviceExpanseAddService;
import com.azu.hospital.ph.StockMangment.addOtherItems.expanse.service.DeviceExpanseServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vi/devices-expanse")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER','PHARMACISTS', 'PHARMACISTS_ASSISTANT', 'ADMINISTRATOR','INVENTORY_CLERK')")
public class DeviceExpanseController {

     private final DeviceExpanseAddService deviceExpanseAddService;
     private final DeviceExpanseServices deviceExpanseServices;

    public DeviceExpanseController(
            DeviceExpanseAddService deviceExpanseAddService,
            DeviceExpanseServices deviceExpanseServices
    ) {
        this.deviceExpanseAddService = deviceExpanseAddService;
        this.deviceExpanseServices = deviceExpanseServices;
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateDevice(@RequestParam Long deviceId,
                             @ModelAttribute UpdateDeviceExpanseRequest update
    ){
        deviceExpanseAddService.updateExpanseDevice(deviceId, update);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable Long deviceId){
        deviceExpanseServices.deleteDeviceById(deviceId);
    }

    @GetMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DeviceExpanseDto> getExistDeviceById(@PathVariable Long deviceId){
        return deviceExpanseServices.getDeviceById(deviceId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DeviceExpanseDto> getAllExpanseDevices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return deviceExpanseServices.getAllDevices(pageable);
    }


}
