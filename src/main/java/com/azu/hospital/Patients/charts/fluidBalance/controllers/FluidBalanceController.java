package com.azu.hospital.Patients.charts.fluidBalance.controllers;

import com.azu.hospital.Patients.charts.fluidBalance.dto.FluidBalanceDto;
import com.azu.hospital.Patients.charts.fluidBalance.request.FluidBalanceRequest;
import com.azu.hospital.Patients.charts.fluidBalance.services.FluidBalanceService;
import com.azu.hospital.Patients.charts.fluidBalance.services.FluidBalanceUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/fluid-balance-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class FluidBalanceController {
  private final FluidBalanceService service;
  private final FluidBalanceUpdateService updateService;

  @Autowired
  public FluidBalanceController(FluidBalanceService service, FluidBalanceUpdateService updateService) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FluidBalanceDto> getAllChart(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public FluidBalanceDto getChartId(
          @PathVariable("chart_id") Long chartId
  ) {
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody FluidBalanceRequest request
  ) {
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody FluidBalanceRequest request
  ) {
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
