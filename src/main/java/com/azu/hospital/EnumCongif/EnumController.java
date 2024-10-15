package com.azu.hospital.EnumCongif;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/enums")
@CrossOrigin

public class EnumController {

    private final EnumServiceConfig service;


    public EnumController(EnumServiceConfig service) {
        this.service = service;
    }


    @GetMapping("get-enums")
    public ResponseEntity<?> getAllEnums(){
        return ResponseEntity.ok(service.scanEnums());
    }
}
