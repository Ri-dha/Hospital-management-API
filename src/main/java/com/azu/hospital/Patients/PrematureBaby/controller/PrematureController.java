package com.azu.hospital.Patients.PrematureBaby.controller;


import com.azu.hospital.Patients.PrematureBaby.dto.PrematureBabyDto;
import com.azu.hospital.Patients.PrematureBaby.requests.PrematureBabyCreateRequest;
import com.azu.hospital.Patients.PrematureBaby.requests.PrematureBabyUpdateRequest;
import com.azu.hospital.Patients.PrematureBaby.service.PrematureBabyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/premature-baby")
@CrossOrigin
public class PrematureController {

    private final PrematureBabyService prematureService;

    @Autowired
    public PrematureController(PrematureBabyService prematureService) {
        this.prematureService = prematureService;
    }



    @PostMapping(value = "/create")
    public ResponseEntity<Void> createPatient(@ModelAttribute PrematureBabyCreateRequest request) {
        prematureService.createPrematureBaby(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Void> updatePatient(@ModelAttribute PrematureBabyUpdateRequest request) {
        prematureService.updatePrematureBaby(request);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        prematureService.deletePrematureBabyById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<PrematureBabyDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(prematureService.findPrematureBabyById(id));
    }

    @GetMapping(value = "/get-by-bedId")
    public ResponseEntity<PrematureBabyDto> getPatientByBedId(@RequestParam Long bedId) {
        return ResponseEntity.ok(prematureService.findPrematureBabyByBedId(bedId));
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Page<PrematureBabyDto>> getAllPatients(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(prematureService.findAllPrematureBaby(pageable));
    }

    @GetMapping(value = "/get-all-by-ward")
    public ResponseEntity<Page<PrematureBabyDto>> getAllPatientsByWard(
            @RequestParam Long wardId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(prematureService.findPrematureBabyByWardId(wardId, pageable));
    }

    @GetMapping(value = "/get-all-by-patient")
    public ResponseEntity<List<PrematureBabyDto>> getAllPatientsByPatient(
            @RequestParam Long patientId) {
        return ResponseEntity.ok(prematureService.findPrematureBabyByPatientId(patientId));
    }



    @PutMapping(value = "/discharge/{id}")
    public ResponseEntity<Void> dischargePatient(@PathVariable Long id) {
        prematureService.dischargePrematureBaby(id);
        return ResponseEntity.ok().build();
    }

}
