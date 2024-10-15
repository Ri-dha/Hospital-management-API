package com.azu.hospital.Patients.charts.nursingObservation.controller;


import com.azu.hospital.Patients.charts.nursingObservation.dto.NursingObservationDto;
import com.azu.hospital.Patients.charts.nursingObservation.request.NursingObservationRequest;
import com.azu.hospital.Patients.charts.nursingObservation.service.NursingObservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/nursing-observation")
@CrossOrigin
public class NursingObservationController {


    private final NursingObservationService service;

    @Autowired
    public NursingObservationController(
            @Qualifier("nursingObservationService") NursingObservationService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Void> createNewChart(
            @RequestParam("patientId") Long patientId,
            @Valid @RequestBody NursingObservationRequest request
    ) {
        service.addNursingObservation(patientId,request);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/{chartId}")
    public ResponseEntity<?> getChartById(
            @PathVariable("chartId") Long chartId
    ) {
        return ResponseEntity.ok(service.getChartById(chartId));
    }

    @GetMapping
    public ResponseEntity<?> getAllCharts(
            @RequestParam("patientId") Long patientId
    ) {
        return ResponseEntity.ok(service.getAllPatientCharts(patientId));
    }


}
