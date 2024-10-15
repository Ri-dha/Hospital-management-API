package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.controller;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PostOperativeFollowUpCallDto;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PostOperativeFollowUpCallChartRequest;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services.PostOperativeFollowUpCallService;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services.PostOperativeFollowUpCallUpdateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/post-operative-follow-up-call-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PostOperativeFollowUpCallController {
  private final PostOperativeFollowUpCallService service;
  private final PostOperativeFollowUpCallUpdateService updateService;

  public PostOperativeFollowUpCallController(
          @Qualifier("PostOperativeFollowUpCallService")
          PostOperativeFollowUpCallService service,
          @Qualifier("PostOperativeFollowUpCallUpdateService")
          PostOperativeFollowUpCallUpdateService updateService) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  public List<PostOperativeFollowUpCallDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllPatientCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  public PostOperativeFollowUpCallDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PostOperativeFollowUpCallChartRequest request
  ){
    return new ResponseEntity<>(
            service.createNewChart(patientId, request),
            HttpStatus.CREATED
    );
  }
  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PostOperativeFollowUpCallChartRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
