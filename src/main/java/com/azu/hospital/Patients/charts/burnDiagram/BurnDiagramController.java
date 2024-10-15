package com.azu.hospital.Patients.charts.burnDiagram;


import com.azu.hospital.Patients.charts.burnDiagram.dto.BurnDiagramDto;
import com.azu.hospital.Patients.charts.burnDiagram.request.BurnDiagramRequest;
import com.azu.hospital.Patients.charts.burnDiagram.services.BurnDiagramService;
import com.azu.hospital.Patients.charts.burnDiagram.services.BurnDiagramUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/charts/burn-diagram-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class BurnDiagramController {
  private final BurnDiagramService service;
  private final BurnDiagramUpdateService updateService;

  @Autowired
  public BurnDiagramController(
          @Qualifier("BurnDiagramService")
          BurnDiagramService service,
          @Qualifier("BurnDiagramUpdateService")
          BurnDiagramUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public BurnDiagramDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<BurnDiagramDto> getAllChartsByPatientId(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getPatientCharts(patientId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody BurnDiagramRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody BurnDiagramRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
