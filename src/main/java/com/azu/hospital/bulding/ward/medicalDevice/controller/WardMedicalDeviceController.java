package com.azu.hospital.bulding.ward.medicalDevice.controller;

import com.azu.hospital.bulding.ward.medicalDevice.dao.WardMedicalDeviceRepository;
import com.azu.hospital.bulding.ward.medicalDevice.request.CreateMedicalDeviceDataRequest;
import com.azu.hospital.bulding.ward.medicalDevice.services.WardMedicalDeviceOrderStatusService;
import com.azu.hospital.bulding.ward.medicalDevice.services.WardMedicalDeviceService;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/ward/medical-device")
@CrossOrigin
public class WardMedicalDeviceController {



    private final WardMedicalDeviceService wardMedicalDevice;

    private final WardMedicalDeviceOrderStatusService wardMedicalDeviceOrderStatusService;

    public WardMedicalDeviceController(WardMedicalDeviceService wardMedicalDevice,
                                       WardMedicalDeviceOrderStatusService wardMedicalDeviceOrderStatusService) {
        this.wardMedicalDevice = wardMedicalDevice;
        this.wardMedicalDeviceOrderStatusService = wardMedicalDeviceOrderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewDevices(@Valid @ModelAttribute CreateMedicalDeviceDataRequest request){
        return ResponseEntity.ok(wardMedicalDevice.createNewWardDevice(request));
    }

    @GetMapping("get")
    public ResponseEntity<?> getAllWithFilter(
            @RequestParam(required = false) WardInventoryRequestEnum status ,
            @RequestParam(defaultValue = "") String itemName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
            ){

        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(wardMedicalDevice.getAllWardDeviceByFilter(status ,
                itemName , pageable));
    }


    @PutMapping("accept-reject")
    public ResponseEntity<?> acceptRejectOrder(@RequestParam Long wardDeviceId ,@RequestParam Boolean isAccept ,
                                               @RequestParam(required = false) String receivedNote){
         wardMedicalDeviceOrderStatusService.acceptOrder(wardDeviceId , isAccept , receivedNote);
         return ResponseEntity.ok("");
    }


    @PutMapping("prepare-pending")
    public ResponseEntity<?> prepareAndPendingOrder(@RequestParam Long wardDeviceId){
         wardMedicalDeviceOrderStatusService.prepareAndPending(wardDeviceId);
         return ResponseEntity.ok("");
    }

    @PutMapping("received-not-received")
    public ResponseEntity<?> receivedNotReceivedOrder(@RequestParam Long wardDeviceId ,
                                                      @RequestParam Boolean isReceived){
         wardMedicalDeviceOrderStatusService.receivedNoReceived(wardDeviceId, isReceived);
         return ResponseEntity.ok("");
    }




    @GetMapping("medical-device-info")
    public ResponseEntity<?> getMedicalDeviceInfo(@RequestParam Long wardId){
        return ResponseEntity.ok(wardMedicalDevice.getMedicalDeviceInfo(wardId));
    }
}
