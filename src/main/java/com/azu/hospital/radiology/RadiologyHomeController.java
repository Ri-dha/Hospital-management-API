package com.azu.hospital.radiology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/radiology")
@CrossOrigin
public class RadiologyHomeController {

    private final RadiologyHomeService radiologyHomeService;

    @Autowired
    public RadiologyHomeController(RadiologyHomeService radiologyHomeService) {
        this.radiologyHomeService = radiologyHomeService;
    }

    @GetMapping("/cards")
    private ResponseEntity<?> getBlock(){
        return ResponseEntity.ok(radiologyHomeService.getBlock());
    }

    @GetMapping("/date-time")
    private ResponseEntity<?> getDateTime(){
        return ResponseEntity.ok(radiologyHomeService.dayTimeAnalysis());
    }

    @GetMapping("/most-use")
    private ResponseEntity<?> getMostUse(){
        return ResponseEntity.ok(radiologyHomeService.mostUse());
    }

}
