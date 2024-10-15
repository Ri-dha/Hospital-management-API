package com.azu.hospital.bulding.unit.unitDevice.controller;

import com.azu.hospital.bulding.unit.unitDevice.request.CreateDeviceDataRequest;
import com.azu.hospital.bulding.unit.unitDevice.services.UnitDeviceOrderStatusService;
import com.azu.hospital.bulding.unit.unitDevice.services.UnitDeviceService;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/unit/device")
@CrossOrigin

public class UnitDeviceController {

    private final UnitDeviceService unitDeviceService;

    private final UnitDeviceOrderStatusService unitDeviceOrderStatusService;

    public UnitDeviceController(UnitDeviceService unitDeviceService, UnitDeviceOrderStatusService unitDeviceOrderStatusService) {
        this.unitDeviceService = unitDeviceService;
        this.unitDeviceOrderStatusService = unitDeviceOrderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewDevices(@Valid @ModelAttribute CreateDeviceDataRequest request){
        return ResponseEntity.ok(unitDeviceService.createNewUnitDevice(request));
    }

    @GetMapping("get")
    public ResponseEntity<?> getAllWithFilter(
            @RequestParam(required = false) UnitInventoryRequestEnum status ,
            @RequestParam(defaultValue = "") String itemName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
            ){

        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(unitDeviceService.getAllUnitDeviceByFilter(status ,
                itemName , pageable));
    }


    @PutMapping("accept-reject")
    public ResponseEntity<?> acceptRejectOrder(@RequestParam Long unitDeviceId ,@RequestParam Boolean isAccept ,
                                               @RequestParam(required = false) String receivedNote){
         unitDeviceOrderStatusService.acceptOrder(unitDeviceId , isAccept , receivedNote);
         return ResponseEntity.ok("");
    }


    @PutMapping("prepare-pending")
    public ResponseEntity<?> prepareAndPendingOrder(@RequestParam Long unitDeviceId){
         unitDeviceOrderStatusService.prepareAndPending(unitDeviceId);
         return ResponseEntity.ok("");
    }

    @PutMapping("received-not-received")
    public ResponseEntity<?> receivedNotReceivedOrder(@RequestParam Long unitDeviceId ,
                                                      @RequestParam Boolean isReceived){
         unitDeviceOrderStatusService.receivedNoReceived(unitDeviceId, isReceived);
         return ResponseEntity.ok("");
    }
}
