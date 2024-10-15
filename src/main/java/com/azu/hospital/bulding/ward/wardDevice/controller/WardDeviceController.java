package com.azu.hospital.bulding.ward.wardDevice.controller;

import com.azu.hospital.bulding.ward.wardDevice.request.CreateDeviceDataRequest;
import com.azu.hospital.bulding.ward.wardDevice.services.WardDeviceOrderStatusService;
import com.azu.hospital.bulding.ward.wardDevice.services.WardDeviceService;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/ward/device")
@CrossOrigin

public class WardDeviceController {

    private final WardDeviceService wardDeviceService;

    private final WardDeviceOrderStatusService wardDeviceOrderStatusService;

    public WardDeviceController(WardDeviceService wardDeviceService, WardDeviceOrderStatusService wardDeviceOrderStatusService) {
        this.wardDeviceService = wardDeviceService;
        this.wardDeviceOrderStatusService = wardDeviceOrderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewDevices(@Valid @RequestBody CreateDeviceDataRequest request){
        return ResponseEntity.ok(wardDeviceService.createNewWardDevice(request));
    }

    @GetMapping("get")
    public ResponseEntity<?> getAllWithFilter(
            @RequestParam(required = false) WardInventoryRequestEnum status ,
            @RequestParam(defaultValue = "") String itemName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
            ){

        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(wardDeviceService.getAllWardDeviceByFilter(status ,
                itemName , pageable));
    }


    @PutMapping("accept-reject")
    public ResponseEntity<?> acceptRejectOrder(@RequestParam Long wardDeviceId ,@RequestParam Boolean isAccept ,
                                               @RequestParam(required = false) String receivedNote){
         wardDeviceOrderStatusService.acceptOrder(wardDeviceId , isAccept , receivedNote);
         return ResponseEntity.ok("");
    }


    @PutMapping("prepare-pending")
    public ResponseEntity<?> prepareAndPendingOrder(@RequestParam Long wardDeviceId){
         wardDeviceOrderStatusService.prepareAndPending(wardDeviceId);
         return ResponseEntity.ok("");
    }

    @PutMapping("received-not-received")
    public ResponseEntity<?> receivedNotReceivedOrder(@RequestParam Long wardDeviceId ,
                                                      @RequestParam Boolean isReceived){
         wardDeviceOrderStatusService.receivedNoReceived(wardDeviceId, isReceived);
         return ResponseEntity.ok("");
    }
}
