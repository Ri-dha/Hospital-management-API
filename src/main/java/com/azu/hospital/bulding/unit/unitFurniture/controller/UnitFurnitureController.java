package com.azu.hospital.bulding.unit.unitFurniture.controller;

import com.azu.hospital.bulding.unit.unitFurniture.request.CreateFurnitureDataRequest;
import com.azu.hospital.bulding.unit.unitFurniture.services.UnitFurnitureOrderStatusService;
import com.azu.hospital.bulding.unit.unitFurniture.services.UnitFurnitureService;
import com.azu.hospital.utils.enums.UnitInventoryRequestEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/unit/furniture")
@CrossOrigin

public class UnitFurnitureController {

    private final UnitFurnitureService unitFurnitureService;

    private final UnitFurnitureOrderStatusService unitFurnitureOrderStatusService;

    public UnitFurnitureController(UnitFurnitureService unitFurnitureService, UnitFurnitureOrderStatusService unitFurnitureOrderStatusService) {
        this.unitFurnitureService = unitFurnitureService;
        this.unitFurnitureOrderStatusService = unitFurnitureOrderStatusService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewFurniture(@Valid @RequestBody CreateFurnitureDataRequest request){
        return ResponseEntity.ok(unitFurnitureService.createNewUnitFurniture(request));
    }


    @GetMapping("get")
    public ResponseEntity<?> getAllWithFilter(
            @RequestParam(required = false) UnitInventoryRequestEnum status ,
            @RequestParam(defaultValue = "") String itemName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
    ){

        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(unitFurnitureService.getAllUnitDeviceByFilter(status ,
                itemName , pageable));
    }


    @PutMapping("accept-reject")
    public ResponseEntity<?> acceptRejectOrder(@RequestParam Long unitDeviceId ,@RequestParam Boolean isAccept ,
                                               @RequestParam String receivedNote){
        unitFurnitureOrderStatusService.acceptOrder(unitDeviceId , isAccept , receivedNote);
        return ResponseEntity.ok("");
    }


    @PutMapping("prepare-pending")
    public ResponseEntity<?> prepareAndPendingOrder(@RequestParam Long unitDeviceId){
        unitFurnitureOrderStatusService.prepareAndPending(unitDeviceId);
        return ResponseEntity.ok("");
    }

    @PutMapping("received-not-received")
    public ResponseEntity<?> receivedNotReceivedOrder(@RequestParam Long unitDeviceId ,
                                                      @RequestParam Boolean isReceived){
        unitFurnitureOrderStatusService.receivedNoReceived(unitDeviceId, isReceived);
        return ResponseEntity.ok("");
    }


}
