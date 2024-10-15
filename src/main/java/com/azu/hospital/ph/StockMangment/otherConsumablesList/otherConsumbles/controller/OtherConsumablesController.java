package com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.controller;


import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.dto.OtherConsumablesDto;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.request.OtherConsumablesRequest;
import com.azu.hospital.ph.StockMangment.otherConsumablesList.otherConsumbles.service.OtherConsumablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/otherConsumables")
@RestController
@CrossOrigin
public class OtherConsumablesController {

    private final OtherConsumablesService service;

    @Autowired
    public OtherConsumablesController(OtherConsumablesService service) {
        this.service = service;
    }


    @PutMapping("/update")
    public ResponseEntity<Void> updateOtherConsumables(
            @RequestBody List<OtherConsumablesRequest> request) {
        service.updateOtherConsumables(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OtherConsumablesDto> getOtherConsumablesById(
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getOtherConsumablesById(id));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllOtherConsumables(
            @RequestParam("patientId") Long patientId) {
        return ResponseEntity.ok(service.getAllOtherConsumables(patientId));
    }

}
