package com.azu.hospital.Patients.charts.preOperativeHPChart;

import com.azu.hospital.Patients.charts.preOperativeHPChart.dto.PreOperativeHPDto;
import com.azu.hospital.Patients.charts.preOperativeHPChart.request.PreOperativeHPChartRequest;
import com.azu.hospital.Patients.charts.preOperativeHPChart.services.PreOperativeHPService;
import com.azu.hospital.Patients.charts.preOperativeHPChart.services.PreOperativeHPUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/pre-operative-hp-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PreOperativeHpChartController {
  private final PreOperativeHPService service;
  private final PreOperativeHPUpdateService updateService;

  @Autowired
  public PreOperativeHpChartController(
          @Qualifier("PreOperativeHPService") PreOperativeHPService service,
          @Qualifier("PreOperativeHPUpdateService") PreOperativeHPUpdateService updateService) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  public List<PreOperativeHPDto> getAllChart(
          @RequestParam("patient_id") Long patientId
  ) {
    return service.getAllPatientChart(patientId);
  }

  @GetMapping("/{chart_id}")
  public PreOperativeHPDto getChartById(
          @PathVariable("chart_id") Long chartId
  ) {
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PreOperativeHPChartRequest request
  ) {
    return ResponseEntity.ok(service.createNewChart(patientId, request));
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PreOperativeHPChartRequest request
  ) {
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }

}
