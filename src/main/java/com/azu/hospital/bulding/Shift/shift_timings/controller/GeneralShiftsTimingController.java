package com.azu.hospital.bulding.Shift.shift_timings.controller;


import com.azu.hospital.bulding.Shift.shift_timings.dto.general.GeneralShiftsTimingDto;
import com.azu.hospital.bulding.Shift.shift_timings.request.GeneralShiftRegistrationsRequest;
import com.azu.hospital.bulding.Shift.shift_timings.request.GeneralShiftUpdateRequest;
import com.azu.hospital.bulding.Shift.shift_timings.services.general.GeneralShiftsGetServices;
import com.azu.hospital.bulding.Shift.shift_timings.services.general.GeneralShiftsTimingAddServices;
import com.azu.hospital.bulding.Shift.shift_timings.services.general.GeneralShiftsTimingUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/general-shift")
@CrossOrigin
public class GeneralShiftsTimingController {

    private final GeneralShiftsTimingAddServices addServices;
    private final GeneralShiftsGetServices getServices;
    private final GeneralShiftsTimingUpdateServices updateServices;

    @Autowired
    public GeneralShiftsTimingController(
            GeneralShiftsTimingAddServices addServices,
            GeneralShiftsGetServices getServices,
            GeneralShiftsTimingUpdateServices updateServices
    ) {
        this.addServices = addServices;
        this.getServices = getServices;
        this.updateServices = updateServices;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> insertGeneralShifts(@RequestBody GeneralShiftRegistrationsRequest request){
        addServices.addGeneralShiftTiming(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<GeneralShiftsTimingDto>> getAllGeneralShifts(){
        return ResponseEntity.ok(getServices.getAllGeneralShiftsTimelines());
    }


    @PutMapping("/update")
    public ResponseEntity<Void> insertGeneralShifts(@RequestParam("id") Long id, @RequestBody GeneralShiftUpdateRequest request){
        updateServices.updateGeneralShiftTiming(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> insertGeneralShifts(@RequestParam("id") Long id){
        updateServices.deleteTimingsArrangeById(id);
        return ResponseEntity.ok().build();
    }

}
