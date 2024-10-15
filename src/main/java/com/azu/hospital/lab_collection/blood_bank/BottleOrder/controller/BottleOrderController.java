package com.azu.hospital.lab_collection.blood_bank.BottleOrder.controller;

import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.AcceptOrderInfo;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.AcceptOrderInfoData;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.request.CreateBottleOrder;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.services.BottleOrderAddService;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.services.BottleOrderGetService;
import com.azu.hospital.lab_collection.blood_bank.BottleOrder.services.BottleOrderService;
import com.azu.hospital.utils.enums.bottles.BottleStateEnum;
import com.azu.hospital.utils.enums.bottles.BottleTypeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lab/blood-bank/bottle/order")
@CrossOrigin
public class BottleOrderController {


    private final BottleOrderAddService bottleOrderService;
    private final BottleOrderGetService getService;

    private final BottleOrderService bottleOrderInfoService;
    @Autowired
    public BottleOrderController(BottleOrderAddService bottleOrderService, BottleOrderGetService getService, BottleOrderService bottleOrderInfoService) {
        this.bottleOrderService = bottleOrderService;
        this.getService = getService;
        this.bottleOrderInfoService = bottleOrderInfoService;
    }

    @GetMapping("/home")
    public ResponseEntity<?> getHome(){
       return ResponseEntity.ok(getService.getBloodBankHome());
    }

    @GetMapping("/get-order")
    public ResponseEntity<?> getOrder(@RequestParam Long orderId){
       return ResponseEntity.ok(getService.getBottleOrderById(orderId));
    }

    @GetMapping("/get-archived-order")
    public ResponseEntity<?> getAllArchivedOrder(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size ,
            @RequestParam(defaultValue = "") String search
    ){
        Pageable pageable = PageRequest.of(page,size);
       return ResponseEntity.ok(getService.getAllArchivedBottleOrder(search , pageable));
    }

    @GetMapping("/get-new-order")
    public ResponseEntity<?> getAllArchivedOrder(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size ,
            @RequestParam(defaultValue = "") String search,
            @RequestParam Boolean isReserved,
            @RequestParam Boolean isPending
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(getService.getNewBottleOrder(
                isReserved ,
                isPending ,
                search ,
                pageable
        ));
    }

   @PostMapping("/new-order")
    public ResponseEntity<?> createNewOrder(
            @Valid @RequestBody CreateBottleOrder request
   ){
       bottleOrderService.createBottleOrder(request);
       return ResponseEntity.ok().build();
    }


    @GetMapping("/get-new-order-with-filter")
    public ResponseEntity<?> getAllNewRequestWithFilter(
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "10") Integer size ,
            @RequestParam(defaultValue = "") String search,
            @RequestParam BottleStateEnum statusEnum
    ){
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(getService.getAllNewRequestWithFilter(statusEnum,search , pageable));
    }

    @PutMapping("/accept-order")
    public void createNewBottleOrderInfo(
            @Valid @RequestBody AcceptOrderInfoData request){
        bottleOrderInfoService.acceptOrder(request);
    }

    @GetMapping("/get-bottle-state-type")
    public ResponseEntity<?> getBottleStateType(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) BottleTypeEnum type,
            @RequestParam(required = false , name = "states") List<BottleStateEnum> states
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(getService.getAllBottleOrdersByTypeAndState(type, states, pageable));
    }

}
