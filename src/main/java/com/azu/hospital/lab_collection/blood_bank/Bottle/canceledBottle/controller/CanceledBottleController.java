package com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.controller;

import com.azu.hospital.lab_collection.blood_bank.Bottle.canceledBottle.services.CanceledBottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/lab/blood-bank/bottle")
@CrossOrigin
public class CanceledBottleController {

    private final CanceledBottleService canceledBottleService;

    @Autowired
    public CanceledBottleController(CanceledBottleService canceledBottleService) {
        this.canceledBottleService = canceledBottleService;
    }

    @PutMapping("/cancel-bottle")
    public void cancelBottle(@RequestParam Long bottleId){
        canceledBottleService.cancelBottle(bottleId);
    }

}
