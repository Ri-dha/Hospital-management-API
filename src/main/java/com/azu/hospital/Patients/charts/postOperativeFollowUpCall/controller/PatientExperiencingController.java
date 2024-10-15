package com.azu.hospital.Patients.charts.postOperativeFollowUpCall.controller;

import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.dto.PatientExperiencingDto;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.request.PatientExperiencingRequest;
import com.azu.hospital.Patients.charts.postOperativeFollowUpCall.services.PatientExperiencingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/post-operative-follow-up-call-chart/patient-experiencing")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PatientExperiencingController {
  private final PatientExperiencingServices services;

  @Autowired
  public PatientExperiencingController(PatientExperiencingServices services) {
    this.services = services;
  }

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  public List<PatientExperiencingDto> getAllPatientExperiencing(
          @RequestParam("chart_id") Long chartId
  ){
    return services.getAllChartExperiencing(chartId);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public void createPatientExperiencing(
          @RequestParam("chart_id") Long chartId,
          @RequestBody List<PatientExperiencingRequest> requests
  ){
    services.createNewChartPatientExperiencing(chartId, requests);
  }

//  @PutMapping
//  @ResponseStatus(code = HttpStatus.OK)
//  public void updatePatientExperiencing(
//          @RequestParam("patient_experiencing_id") Long experiencingId,
//          @RequestBody PatientExperiencingRequest request
//  ){
//    services.updatePatientExperiencing(experiencingId, request);
//  }

}
