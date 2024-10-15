package com.azu.hospital.Patients.charts.base_chart.controller;

import com.azu.hospital.Patients.charts.base_chart.service.BaseChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charts")
@CrossOrigin
public class BaseChartController {

  private final BaseChartService chartService;


  @Autowired
  public BaseChartController(BaseChartService chartService) {
    this.chartService = chartService;
  }


  @GetMapping("/get")
  public ResponseEntity<?> getAllPatientCharts(
              @RequestParam("patientId") Long patientId,
          @RequestParam(value = "page", defaultValue = "0") int page,
          @RequestParam(value = "size", defaultValue = "10") int size

  ){
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(chartService.getAllChartsByPatientId(patientId,pageable));
  }



@GetMapping("/{id}")
  public ResponseEntity<?> getChartsById(
          @PathVariable("id") Long id

  ){
    return ResponseEntity.ok(chartService.getChartById(id));
  }

}


