package com.azu.hospital.bulding.Shift.controller;

import com.azu.hospital.bulding.Shift.request.ShiftAddRequest;
import com.azu.hospital.bulding.Shift.services.ShiftService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }



    @GetMapping
    public ResponseEntity<?> getAllShifts(
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(shiftService.getAllShifts(pageable));
    }


    @PostMapping
    public void createShift(
            @Valid  @RequestBody ShiftAddRequest request
    ) {
        shiftService.createShift(request);
    }

    @PutMapping("/{id}")
    public void updateShift(
            @Valid @RequestBody ShiftAddRequest request,
            @PathVariable Long id
    ) {
        shiftService.updateShift(request, id);
    }






}
