package com.azu.hospital.Patients.charts.preMedicalAssessment;


import com.azu.hospital.Patients.charts.preMedicalAssessment.request.PreMedicalAssessmentRequest;
import com.azu.hospital.Patients.charts.preMedicalAssessment.services.PreMedicalAssessmentService;
import com.azu.hospital.Patients.charts.preMedicalAssessment.services.PreMedicalAssessmentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charts/pre-medical-assessment-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PreMedicalAssessmentController {
  private final PreMedicalAssessmentService service;
  private final PreMedicalAssessmentUpdateService updateService;

  @Autowired
  public PreMedicalAssessmentController(
          @Qualifier("PreMedicalAssessmentService") PreMedicalAssessmentService service,
          @Qualifier("PreMedicalAssessmentUpdateService") PreMedicalAssessmentUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  public ResponseEntity<?> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return ResponseEntity.ok(service.getAllPatientCharts(patientId));
  }

  @GetMapping("/{chart_id}")
  public ResponseEntity<?> getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return ResponseEntity.ok(service.getChartById(chartId));
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PreMedicalAssessmentRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PreMedicalAssessmentRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
