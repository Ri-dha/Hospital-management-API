package com.azu.hospital.Patients.charts.painManagment;

import com.azu.hospital.Patients.charts.painManagment.dto.PainManagementDto;
import com.azu.hospital.Patients.charts.painManagment.request.PainManagementRequest;
import com.azu.hospital.Patients.charts.painManagment.services.PainManagementService;
import com.azu.hospital.Patients.charts.painManagment.services.PainManagementUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/pain-management-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PainManagementController {
  private final PainManagementService service;
  private final PainManagementUpdateService updateService;

  @Autowired
  public PainManagementController(
          @Qualifier("PainManagementService")
          PainManagementService service,
          @Qualifier("PainManagementUpdateService")
          PainManagementUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<PainManagementDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllCharts(patientId);
  }
  @GetMapping("/{chart_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public PainManagementDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PainManagementRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<?> update(
          @RequestParam("patient_id") Long patientId,
          @RequestParam("chart_id") Long chartId,
          @RequestBody PainManagementRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(patientId, chartId, request));
  }
}
