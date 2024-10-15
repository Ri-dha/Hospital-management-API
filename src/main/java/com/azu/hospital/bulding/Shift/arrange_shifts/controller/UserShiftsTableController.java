package com.azu.hospital.bulding.Shift.arrange_shifts.controller;

import com.azu.hospital.bulding.Shift.arrange_shifts.services.user_shift.UserShiftsTableAddServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shifts-users")
@CrossOrigin
public class UserShiftsTableController {

    private final UserShiftsTableAddServices addServices;

    @Autowired
    public UserShiftsTableController(UserShiftsTableAddServices addServices) {
        this.addServices = addServices;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUserToShifts(
            @RequestParam("id") Long userId,
            @RequestParam("id") Long wardId,
            @RequestParam("id") Long shiftId
    ){
        addServices.insertUserShiftsTable(userId, wardId, shiftId);
        return ResponseEntity.ok().build();
    }
}
