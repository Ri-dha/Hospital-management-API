package com.azu.hospital.Patients.charts.fluidBalance.controllers;

import com.azu.hospital.Patients.charts.fluidBalance.request.FluidOutputRequest;
import com.azu.hospital.Patients.charts.fluidBalance.services.FluidOutputService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charts/fluid-balance-chart/output-table")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class FluidOutputController {
  private final FluidOutputService service;

  public FluidOutputController(FluidOutputService service) {
    this.service = service;
  }


  @GetMapping
  public ResponseEntity<?> getFluidOutputTable(
          @RequestParam("chart_id") Long chartId
  ) {
    return new ResponseEntity<>(service.getFluidOutputTable(chartId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addNewFluidOutput(
          @RequestParam("chart_id") Long chartId,
          @RequestBody FluidOutputRequest request
  ) {
    return new ResponseEntity<>(
            service.addNewFluidOutput(
                    chartId, request
            ),
            HttpStatus.CREATED
    );
  }

  @PutMapping
  public ResponseEntity<?> updateFluidOutput(
          @RequestParam("row_id") Long rowId,
          @RequestParam("chart_id") Long chartId,
          @RequestBody FluidOutputRequest request
  ) {
    return new ResponseEntity<>(
            service.updateFluidInput(
                    rowId,
                    chartId,
                    request
            ),
            HttpStatus.CREATED
    );
  }
}
