package com.azu.hospital.Patients.charts.postDischargeInstructions;

import com.azu.hospital.Patients.charts.postDischargeInstructions.dto.PostDischargeInstructionsDto;
import com.azu.hospital.Patients.charts.postDischargeInstructions.request.PostDischargeInstructionRequest;
import com.azu.hospital.Patients.charts.postDischargeInstructions.services.PostDischargeInstructionsService;
import com.azu.hospital.Patients.charts.postDischargeInstructions.services.PostDischargeInstructionsUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/post-discharge-instructions-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PostDischargeInstructionsController {

  private final PostDischargeInstructionsService service;
  private final PostDischargeInstructionsUpdateService updateService;

  @Autowired
  public PostDischargeInstructionsController(
          @Qualifier("PostDischargeInstructionsService")
          PostDischargeInstructionsService service,
          @Qualifier("PostDischargeInstructionsUpdateService")
          PostDischargeInstructionsUpdateService updateService
  ) {
    this.service = service;
    this.updateService = updateService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PostDischargeInstructionsDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return service.getAllPatientCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public PostDischargeInstructionsDto getChartById(
          @PathVariable("chart_id") Long chartId
  ){
    return service.getChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> createNewChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody PostDischargeInstructionRequest request
    ){
    return new ResponseEntity<>(service.createNewChart(patientId, request), HttpStatus.CREATED);
  }

  @PutMapping("/{chart_id}")
  public ResponseEntity<?> updateExistsChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody PostDischargeInstructionRequest request
  ){
    return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
  }
}
