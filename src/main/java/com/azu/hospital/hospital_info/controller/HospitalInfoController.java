package com.azu.hospital.hospital_info.controller;


import com.azu.hospital.hospital_info.request.HospitalInfoRequest;
import com.azu.hospital.hospital_info.service.HospitalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/v1/hospital_info")
@RestController
@CrossOrigin
public class HospitalInfoController {

    private final HospitalInfoService service;


    @Autowired
    public HospitalInfoController(HospitalInfoService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createHospitalInfo(
            @ModelAttribute("hospitalInfo") HospitalInfoRequest request
    ) throws IOException {
        service.createHospitalInfo(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHospitalInfoById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(service.getHospitalInfoById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHospitalInfo(
            @PathVariable("id") Long id,
            @ModelAttribute("hospitalInfo") HospitalInfoRequest request
    ) throws IOException {
        service.updateHospitalInfo(id, request);
        return ResponseEntity.ok().build();
    }
}
