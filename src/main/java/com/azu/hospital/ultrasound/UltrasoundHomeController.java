package com.azu.hospital.ultrasound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ultrasound")
@CrossOrigin

public class UltrasoundHomeController {

    private final UltrasoundHomeService ultrasoundHomeService;

    @Autowired
    public UltrasoundHomeController(UltrasoundHomeService ultrasoundHomeService) {
        this.ultrasoundHomeService = ultrasoundHomeService;
    }

    @GetMapping("cards")
    private ResponseEntity<?> getBlock(){
        return ResponseEntity.ok(ultrasoundHomeService.getBlock());
    }

    @GetMapping("date-time")
    private ResponseEntity<?> getDateTime(){
        return ResponseEntity.ok(ultrasoundHomeService.dayTimeAnalysis());
    }
}
