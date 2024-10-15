package com.azu.hospital.Patients.charts.patientConsentAnesthesia;

import com.azu.hospital.Patients.charts.patientConsentAnesthesia.dto.PatientConsentAnesthesiaDto;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.request.PatientConsentAnesthesiaRequest;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.services.PatientConsentAnesthesiaService;
import com.azu.hospital.Patients.charts.patientConsentAnesthesia.services.PatientConsentAnesthesiaUpdateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/patient-consent-anesthesia-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PatientConsentAnesthesiaController {

  private final PatientConsentAnesthesiaService service;
  private final PatientConsentAnesthesiaUpdateService updateService;

  public PatientConsentAnesthesiaController(
          @Qualifier("PatientConsentAnesthesiaService")
          PatientConsentAnesthesiaService service,
          @Qualifier("PatientConsentAnesthesiaUpdateService")
          PatientConsentAnesthesiaUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping("/{chart_id}")
  public PatientConsentAnesthesiaDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<PatientConsentAnesthesiaDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllPatientChart(patientId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PatientConsentAnesthesiaRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PatientConsentAnesthesiaRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
