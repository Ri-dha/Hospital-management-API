package com.azu.hospital.bulding.Shift.shift_timings.controller;

import com.azu.hospital.bulding.Shift.shift_timings.request.CostumeShiftUpdateRequest;
import com.azu.hospital.bulding.Shift.shift_timings.services.costume.CostumeShiftAddServices;
import com.azu.hospital.bulding.Shift.shift_timings.services.costume.CostumeShiftGetServices;
import com.azu.hospital.bulding.Shift.shift_timings.dto.costume.CostumeShiftDto;
import com.azu.hospital.bulding.Shift.shift_timings.request.CostumeShiftRegistrationsRequest;
import com.azu.hospital.bulding.Shift.shift_timings.services.costume.CostumeShiftUpdateServices;
import com.azu.hospital.utils.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/costume-shift")
@CrossOrigin
public class CostumeShiftController {

    private final CostumeShiftAddServices addServices;
    private final CostumeShiftGetServices getServices;
    private final CostumeShiftUpdateServices updateServices;

    @Autowired
    public CostumeShiftController(CostumeShiftAddServices addServices,
                                  CostumeShiftGetServices getServices,
                                  CostumeShiftUpdateServices updateServices) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addCostumeShifts(@RequestBody CostumeShiftRegistrationsRequest request){
        addServices.addCostumeShift(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostumeShiftDto> getCostumeShiftDto(@PathVariable("id") Long id){
        return ResponseEntity.ok(getServices.getCostumeShiftById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CostumeShiftDto>> getAllCostumeShiftDto(){
        return ResponseEntity.ok(getServices.getAllCostumeShifts());
    }

    @GetMapping("/get-all-by-ward-id")
    public ResponseEntity<List<CostumeShiftDto>> getAllCostumeShiftDtoByWardId(
            @RequestParam("wardId") Long wardId,
            @RequestParam(value = "role", required = false) EnumRole role){
        return ResponseEntity.ok(getServices.getAllCostumeShiftsByWardId(wardId,role));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCostumeShifts(@RequestParam("id") Long id, @RequestBody CostumeShiftUpdateRequest request){
        updateServices.updateCostumeShift(id, request);
        return ResponseEntity.ok().build();
    }
}
