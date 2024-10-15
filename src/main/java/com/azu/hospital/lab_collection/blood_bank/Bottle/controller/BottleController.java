package com.azu.hospital.lab_collection.blood_bank.Bottle.controller;

import com.azu.hospital.lab_collection.blood_bank.Bottle.request.CreateNewBottleRequest;
import com.azu.hospital.lab_collection.blood_bank.Bottle.services.BottleOrderServiceState;
import com.azu.hospital.lab_collection.blood_bank.Bottle.services.BottleService;
import com.azu.hospital.lab_collection.blood_bank.Bottle.services.BottleUpdateService;
import com.azu.hospital.lab_collection.blood_bank.Bottle.services.HomeCardBottleService;
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
@RequestMapping("api/v1/lab/blood-bank/bottle")
@CrossOrigin
public class BottleController {

    private final BottleService bottleService;
    private final BottleOrderServiceState bottleOrderServiceState;

    private final HomeCardBottleService homeCardBottleService;

    private final BottleUpdateService updateService;

    @Autowired
    public BottleController(
            BottleService bottleService,
            BottleOrderServiceState bottleOrderServiceState, HomeCardBottleService homeCardBottleService, BottleUpdateService updateService
    ) {
        this.bottleService = bottleService;
        this.bottleOrderServiceState = bottleOrderServiceState;
        this.homeCardBottleService = homeCardBottleService;
        this.updateService = updateService;
    }


    @GetMapping("/no-archived")
    public ResponseEntity<?> getNotArchiveBottle(
            @RequestParam BottleTypeEnum type,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bottleService.getNotArchiveBottle(
                type,
                search,
                pageable
        ));
    }

    @PostMapping("/add-bottle")
    public ResponseEntity<?> createNewBottle(@Valid @ModelAttribute CreateNewBottleRequest request) {
        bottleService.createNewBottle(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("count")
    public ResponseEntity<?> getCountOfBottle(@RequestParam BottleTypeEnum bottleType) {
        return ResponseEntity.ok(bottleService.getCountOfBottle(bottleType));
    }

    @PutMapping("/prepare-bottle")
    public void cancelBottle(@RequestParam Long bottleId) {
        bottleOrderServiceState.prepareBottle(bottleId);
    }

    @PutMapping("/ready-bottle")
    public void readyBottle(@RequestParam Long bottleId) {
        bottleOrderServiceState.completePrepareBottle(bottleId);
    }

    @PutMapping("/received-bottle")
    public void receivedBottle(@RequestParam Long bottleId) {
        bottleOrderServiceState.receivedBottle(bottleId);
    }

    @PutMapping("/not-received-bottle")
    public void notReceivedBottle(@RequestParam Long bottleId) {
        bottleOrderServiceState.notReceivedBottle(bottleId);
    }


    @GetMapping("/home-card")
    public ResponseEntity<?> getHomeCardBottle(){
        return ResponseEntity.ok(homeCardBottleService.getHomeCardBottle());
    }

    @PutMapping("/update-bottle")
    public void updateBottle(
            @RequestParam Long bottleId ,
            @Valid @ModelAttribute CreateNewBottleRequest request){
        updateService.updateBottleState(bottleId , request);
    }

    @GetMapping("/get-by-state")
    public ResponseEntity<?> getBottleByStateAndBottleType(
            @RequestParam(required = false) List<BottleStateEnum> states,
            @RequestParam BottleTypeEnum bottleType,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bottleService.getBottleByStateAndBottleType(
                states,
                bottleType,
                pageable
        ));
    }

    @GetMapping("/archive")
    public ResponseEntity<?> getArchiveBottle(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bottleService.getBottleArchive(
                pageable
        ));
    }

}
