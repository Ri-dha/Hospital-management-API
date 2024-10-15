package com.azu.hospital.bulding.ward.wardBed.controller;

import com.azu.hospital.bulding.ward.wardBed.request.CreateBedRequest;
import com.azu.hospital.bulding.ward.wardBed.service.BedService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/ward/bed")
@CrossOrigin

public class BedController {


    private final BedService bedService;

    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @GetMapping("get-all-by-ward")
    public ResponseEntity<?> getAllByWardId(
            @RequestParam (required = false) Long wardId,
            @RequestParam(defaultValue = "false") Boolean isReserved,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "15") Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bedService.getAllBedByWard(wardId, isReserved, pageable));

    }

    @PostMapping("/add")
    public ResponseEntity<Void> createNewBed(@ModelAttribute CreateBedRequest request) {
        bedService.createNewBed(request);
        return ResponseEntity.ok().build();

    }

    @GetMapping("status")
    public ResponseEntity<?> getBedStatus(@RequestParam Long wardId){
        return ResponseEntity.ok(bedService.getBedStatus(wardId));
    }


    @PutMapping("change-patient-bed")
    public void changePatientBed(@RequestParam Long patientId , @RequestParam Long bedId){
        bedService.changePatientWard(patientId,bedId);
    }

    @PutMapping("update/{bedId}")
    public void updateBed(@RequestBody String bedNumber , @PathVariable Long bedId){
        bedService.updateBed(bedId, bedNumber);
    }

    @PutMapping("/update/block-bed")
    @ResponseStatus(HttpStatus.OK)
    public void blockBed(@RequestParam("bedId") Long bedId){
        bedService.blockBedById(bedId);
    }

    @PutMapping("/update/unblock-bed")
    @ResponseStatus(HttpStatus.OK)
    public void unBlockBed(@RequestParam("bedId") Long bedId){
        bedService.unblockBedById(bedId);
    }

    @GetMapping("/get-by-floor")
    public ResponseEntity<?> getBedByFloorId(@RequestParam Long floorId , @RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "15") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bedService.getAllBedsInFloor(floorId,pageable));
    }


    @GetMapping("/get-by-id")
    public ResponseEntity<?> getBedById(@RequestParam Long bedId){
        return ResponseEntity.ok(bedService.getBedById(bedId));
    }


}
