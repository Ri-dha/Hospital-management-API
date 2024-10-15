package com.azu.hospital.Patients.charts.fluidBalance.controllers;

import com.azu.hospital.Patients.charts.fluidBalance.request.FluidIntakeRequest;
import com.azu.hospital.Patients.charts.fluidBalance.services.FluidIntakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/charts/fluid-balance-chart/input-table")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class FluidIntakeController {
  private final FluidIntakeService service;

  public FluidIntakeController(FluidIntakeService service) {
    this.service = service;
  }


  @GetMapping
  public ResponseEntity<?> getFluidBalanceInputTable(
          @RequestParam("chart_id") Long chartId
  ) {
    return new ResponseEntity<>(service.getFluidBalanceInputTable(chartId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addNewFluidInput(
          @RequestParam("chart_id") Long chartId,
          @RequestBody FluidIntakeRequest request
  ) {
    return new ResponseEntity<>(service.addNewFluidInput(chartId, request), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<?> updateFluidInput(
          @RequestParam("row_id") Long rowId,
          @RequestParam("chart_id") Long chartId,
          @RequestBody FluidIntakeRequest request
  ) {
    return new ResponseEntity<>(service.updateFluidInput(rowId, chartId, request), HttpStatus.CREATED);
  }
}
