package com.azu.hospital.Patients.charts.procedureAnesthesiaConsent;

import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.dto.ProcedureAnesthesiaConsentDto;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.request.ProcedureAnesthesiaConsentRequest;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.services.ProcedureAnesthesiaConsentService;
import com.azu.hospital.Patients.charts.procedureAnesthesiaConsent.services.ProcedureAnesthesiaConsentUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/procedure-anesthesia-consent-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class ProcedureAnesthesiaConsentController {
  private final ProcedureAnesthesiaConsentService service;
  private final ProcedureAnesthesiaConsentUpdateService updateService;

  @Autowired
  public ProcedureAnesthesiaConsentController(
          @Qualifier("ProcedureAnesthesiaConsentService")
          ProcedureAnesthesiaConsentService service,
          @Qualifier("ProcedureAnesthesiaConsentUpdateService")
          ProcedureAnesthesiaConsentUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProcedureAnesthesiaConsentDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllPatientChart(patientId);
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public ProcedureAnesthesiaConsentDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }


  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody ProcedureAnesthesiaConsentRequest request
  ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody ProcedureAnesthesiaConsentRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
