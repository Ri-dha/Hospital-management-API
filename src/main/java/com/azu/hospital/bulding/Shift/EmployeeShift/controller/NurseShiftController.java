package com.azu.hospital.bulding.Shift.EmployeeShift.controller;

import com.azu.hospital.bulding.Shift.EmployeeShift.request.AddUserToShiftRequest;
import com.azu.hospital.bulding.Shift.EmployeeShift.request.EmployeeShiftRequest;
import com.azu.hospital.bulding.Shift.EmployeeShift.request.EmployeeShiftUpdate;
import com.azu.hospital.bulding.Shift.EmployeeShift.services.EmployeeShiftService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nurse-shift")
public class NurseShiftController {


    private final EmployeeShiftService nurseShiftService;

    public NurseShiftController(EmployeeShiftService nurseShiftService) {
        this.nurseShiftService = nurseShiftService;
    }


    @PostMapping
    public void createNurseShift(
            @Valid @RequestBody EmployeeShiftRequest request
    ) {

        nurseShiftService.createNurseShift(request);
    }

    @PutMapping("/{id}")
    public void updateNurseShift(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeeShiftUpdate request
    ) {

        nurseShiftService.updateNurseShift(request, id);
    }


    @PostMapping("/add-nurse")
    public void addNurseToShift(
            @Valid @RequestBody AddUserToShiftRequest request
    ) {
        nurseShiftService.addNurseToShift(request);
    }

    @GetMapping
    public ResponseEntity<?> getAllNurseShifts(
            @PageableDefault Pageable pageable
    ) {
         return ResponseEntity.ok(nurseShiftService.getAllNurseShifts(pageable));
    }



    @GetMapping("/{nurseId}")
    public ResponseEntity<?> getNurseShiftsByNurseId(
            @PathVariable("nurseId") Long nurseId ,
            @RequestParam("date") String date
    ) {
        return ResponseEntity.ok(nurseShiftService.getEmployeeShiftsByUserId(nurseId , date));
    }

}
