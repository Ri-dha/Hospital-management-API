package com.azu.hospital.Patients.charts.discharge;

import com.azu.hospital.Patients.charts.discharge.dto.DischargeDto;
import com.azu.hospital.Patients.charts.discharge.request.DischargeRequest;
import com.azu.hospital.Patients.charts.discharge.services.DischargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/discharge")
@CrossOrigin
public class DischargeController {
    private final DischargeService service;

    @Autowired
    public DischargeController(DischargeService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<DischargeDto> getAllCharts(
            @RequestParam("patient_id") Long patientId
    ) {
        return service.getAllCharts(patientId);
    }

    @GetMapping("/{chart_id}")
    @ResponseStatus(HttpStatus.OK)
    public DischargeDto getChartById(
            @PathVariable("chart_id") Long chartId
    ) {
        return service.getChartById(chartId);
    }


    @PostMapping("/add")
    public ResponseEntity<?> createNewChart(
            @RequestParam("patient_id") Long patientId,
            @ModelAttribute DischargeRequest request
    ) throws IOException {
        return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateChart(
            @RequestParam("chart_id") Long chartId,
            @RequestParam("patient_id") Long patientId,
            @RequestBody DischargeRequest request
    ) {
        service.updateExistsChart(chartId, patientId, request);
    }
}
