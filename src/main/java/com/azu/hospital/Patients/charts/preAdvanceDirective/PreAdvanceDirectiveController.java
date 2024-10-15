package com.azu.hospital.Patients.charts.preAdvanceDirective;

import com.azu.hospital.Patients.charts.preAdvanceDirective.dto.PreAdvanceDirectiveDto;
import com.azu.hospital.Patients.charts.preAdvanceDirective.request.PreAdvanceDirectiveRequest;
import com.azu.hospital.Patients.charts.preAdvanceDirective.services.PreAdvanceDirectiveService;
import com.azu.hospital.Patients.charts.preAdvanceDirective.services.PreAdvanceDirectiveUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/pre-advance-directive-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PreAdvanceDirectiveController {
  private final PreAdvanceDirectiveService service;
  private final PreAdvanceDirectiveUpdateService updateService;

  @Autowired
  public PreAdvanceDirectiveController(
          @Qualifier("PreAdvanceDirectiveService")
          PreAdvanceDirectiveService service,
          @Qualifier("PreAdvanceDirectiveUpdateService")
          PreAdvanceDirectiveUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PreAdvanceDirectiveDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllPatientCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public PreAdvanceDirectiveDto getChartByID(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PreAdvanceDirectiveRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PreAdvanceDirectiveRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
