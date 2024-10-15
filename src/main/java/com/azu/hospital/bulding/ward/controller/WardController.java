package com.azu.hospital.bulding.ward.controller;

import com.azu.hospital.bulding.ward.request.CreateWardRequest;
import com.azu.hospital.bulding.ward.request.UpdateWardRequest;
import com.azu.hospital.bulding.ward.services.WardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department/wards")
@CrossOrigin
public class  WardController {

    private final WardService wardService;

    @Autowired
    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNewWard(@Valid @ModelAttribute CreateWardRequest request) {
        wardService.createNewWard(request);
        return ResponseEntity.ok().build();
    }

    ;


    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateWard(
            @Valid @ModelAttribute UpdateWardRequest request, @PathVariable Long id) {
        wardService.updateWard(request, id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get-by-dep-id")
    public ResponseEntity<?> getWardByDepId(
            @RequestParam Long depId
                                  ) {
        return ResponseEntity.ok(wardService.getAllWardByDepId(depId));
    }


    @GetMapping("get-by-floor-id")
    public ResponseEntity<?> getWardByFloorId(@RequestParam Long floorId) {
        return ResponseEntity.ok(wardService.getAllWardByFloorId(floorId));
    }


    @GetMapping("get-all")
    public ResponseEntity<?> getAllWard(
            @RequestParam(defaultValue = "", required = false) String wardName ,
            @RequestParam(defaultValue = "0") Integer page ,
            @RequestParam(defaultValue = "15") Integer size
    ){
        Pageable pageable = PageRequest.of(page , size);
        return ResponseEntity.ok(wardService.getAllWard(wardName , pageable));
    };

    @GetMapping("get-doctors-by-ward-id")
    public ResponseEntity<?> getDoctorsByWardId(@RequestParam Long wardId){
        return ResponseEntity.ok(wardService.getDoctorsByWardId(wardId));
    }

    @GetMapping("get-nurses-by-ward-id")
    public ResponseEntity<?> getNursesByWardId(@RequestParam Long wardId){
        return ResponseEntity.ok(wardService.getNursesByWardId(wardId));
    }

    @GetMapping("get-ward-by-nurse-id")
    public ResponseEntity<?> getWardByNurseId(@RequestParam Long nurseId){
        return ResponseEntity.ok(wardService.getWardByNurseId(nurseId));
    }

}
