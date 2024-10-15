package com.azu.hospital.Patients.charts.nusringCarePlan.controller;

import com.azu.hospital.Patients.charts.nusringCarePlan.dto.NursingCarePlanDto;
import com.azu.hospital.Patients.charts.nusringCarePlan.request.NursingCarePlanRequest;
import com.azu.hospital.Patients.charts.nusringCarePlan.service.NursingCarePlanService;
import com.azu.hospital.Patients.charts.nusringCarePlan.service.NursingCarePlanUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/nursing-care-plan")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class NursingCarePlanController {
  private final NursingCarePlanService nursingCarePlanService;

  private final NursingCarePlanUpdateService nursingCarePlanUpdateService;

  @Autowired
  public NursingCarePlanController(NursingCarePlanService nursingCarePlanService, NursingCarePlanUpdateService nursingCarePlanUpdateService) {
    this.nursingCarePlanService = nursingCarePlanService;
    this.nursingCarePlanUpdateService = nursingCarePlanUpdateService;
  }

  @GetMapping
  public List<NursingCarePlanDto> getAllCharts(
          @RequestParam("patientId") Long patientId
  ){
    return nursingCarePlanService.getAllChart(patientId);
  }

  @GetMapping("/{chart_id}")
  public NursingCarePlanDto getNursingAssessmentChartById(
          @PathVariable("chart_id") Long chartId
  ) {
    return nursingCarePlanService.getNursingCarePlan(chartId);
  }


  @PostMapping
  public ResponseEntity<?> addNursingChart(
          @RequestParam("patientId") Long patientId,
          @RequestBody NursingCarePlanRequest request
  ) {
    return new ResponseEntity<>(
            nursingCarePlanService.addNursingCareChart(patientId, request),
            HttpStatus.CREATED
    );
  }

  @PutMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> updateNursingChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody NursingCarePlanRequest request
  ) {
    return ResponseEntity.ok(
            nursingCarePlanUpdateService
                    .updateNursingCareChart(request, chartId)
    );
  }

}
