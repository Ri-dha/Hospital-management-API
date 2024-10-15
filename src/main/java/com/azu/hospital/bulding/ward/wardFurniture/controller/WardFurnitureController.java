package com.azu.hospital.bulding.ward.wardFurniture.controller;

import com.azu.hospital.bulding.ward.wardFurniture.request.CreateFurnitureDataRequest;
import com.azu.hospital.bulding.ward.wardFurniture.services.WardFurnitureOrderStatusService;
import com.azu.hospital.bulding.ward.wardFurniture.services.WardFurnitureService;
import com.azu.hospital.utils.enums.WardInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/ward/furniture")
@CrossOrigin

public class WardFurnitureController {

    private final WardFurnitureService wardFurnitureService;
    private final WardFurnitureOrderStatusService wardFurnitureOrderStatusService;

    @Autowired
    public WardFurnitureController(
            WardFurnitureService wardFurnitureService,
            WardFurnitureOrderStatusService wardFurnitureOrderStatusService
    ) {
        this.wardFurnitureService = wardFurnitureService;
        this.wardFurnitureOrderStatusService = wardFurnitureOrderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewFurniture(@Valid @RequestBody CreateFurnitureDataRequest request){
        return ResponseEntity.ok(wardFurnitureService.createNewWardFurniture(request));
    }


    @GetMapping("/get")
    public ResponseEntity<?> getAllWithFilter(
            @RequestParam(required = false) WardInventoryRequestEnum status ,
            @RequestParam(defaultValue = "") String itemName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
    ){

        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(wardFurnitureService.getAllWardDeviceByFilter(status ,
                itemName , pageable));
    }


    @PutMapping("/accept-reject")
    public ResponseEntity<Void> acceptRejectOrder(@RequestParam Long wardDeviceId ,@RequestParam Boolean isAccept ,
                                               @RequestParam(required = false) String receivedNote){
        wardFurnitureOrderStatusService.acceptOrder(wardDeviceId , isAccept , receivedNote);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/prepare-pending")
    public ResponseEntity<Void> prepareAndPendingOrder(@RequestParam Long wardDeviceId){
        wardFurnitureOrderStatusService.prepareAndPending(wardDeviceId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/received-not-received")
    public ResponseEntity<Void> receivedNotReceivedOrder(@RequestParam Long wardDeviceId ,
                                                      @RequestParam Boolean isReceived){
        wardFurnitureOrderStatusService.receivedNoReceived(wardDeviceId, isReceived);
        return ResponseEntity.ok().build();
    }


}
