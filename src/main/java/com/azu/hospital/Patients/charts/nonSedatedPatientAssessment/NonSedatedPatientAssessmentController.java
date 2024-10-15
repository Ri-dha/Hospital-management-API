package com.azu.hospital.Patients.charts.nonSedatedPatientAssessment;

import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.dto.NonSedatedPatientAssessmentDto;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.request.NonSedatedPatientAssessmentRequest;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.services.NonSedatedPatientAssessmentService;
import com.azu.hospital.Patients.charts.nonSedatedPatientAssessment.services.NonSedatedPatientAssessmentUpdateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/non-sedated-patient-assessment-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class NonSedatedPatientAssessmentController {
  private final NonSedatedPatientAssessmentService service;
  private final NonSedatedPatientAssessmentUpdateService updateService;

  public NonSedatedPatientAssessmentController(
          @Qualifier("NonSedatedPatientAssessmentService")
          NonSedatedPatientAssessmentService service,

          @Qualifier("NonSedatedPatientAssessmentUpdateService")
          NonSedatedPatientAssessmentUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<NonSedatedPatientAssessmentDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(code = HttpStatus.OK)
  public NonSedatedPatientAssessmentDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }
  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody NonSedatedPatientAssessmentRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<?> updateExistsChart(
          @RequestParam("patient_id") Long patientId,
          @RequestParam("chart_id") Long chartId,
          @RequestBody NonSedatedPatientAssessmentRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(patientId, chartId, request));
  }

}
