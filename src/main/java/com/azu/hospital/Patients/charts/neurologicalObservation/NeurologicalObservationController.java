package com.azu.hospital.Patients.charts.neurologicalObservation;


import com.azu.hospital.Patients.charts.neurologicalObservation.dto.NeurologicalObservationDto;
import com.azu.hospital.Patients.charts.neurologicalObservation.request.NeurologicalObservationRequest;
import com.azu.hospital.Patients.charts.neurologicalObservation.services.NeurologicalObservationService;
import com.azu.hospital.Patients.charts.neurologicalObservation.services.NeurologicalObservationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/neurological-observation-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class NeurologicalObservationController {
  private final NeurologicalObservationService service;
  private final NeurologicalObservationUpdateService updateService;

  @Autowired
  public NeurologicalObservationController(
          @Qualifier("NeurologicalObservationService")
          NeurologicalObservationService service,
          @Qualifier("NeurologicalObservationUpdateService")
          NeurologicalObservationUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<NeurologicalObservationDto> getAllChartByPatientId(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllCharts(patientId);
  }
  @GetMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public NeurologicalObservationDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody NeurologicalObservationRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody NeurologicalObservationRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
