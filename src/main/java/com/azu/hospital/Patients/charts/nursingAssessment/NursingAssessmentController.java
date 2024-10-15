package com.azu.hospital.Patients.charts.nursingAssessment;

import com.azu.hospital.Patients.charts.nursingAssessment.dto.NursingAssessmentChartDto;
import com.azu.hospital.Patients.charts.nursingAssessment.request.NursingAssessmentChartRequest;
import com.azu.hospital.Patients.charts.nursingAssessment.services.NursingAssessmentAddServices;
import com.azu.hospital.Patients.charts.nursingAssessment.services.NursingAssessmentUpdateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/charts/nursing-assessment-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class NursingAssessmentController {
  private final NursingAssessmentAddServices nursingAssessmentAddServices;
  private final NursingAssessmentUpdateServices nursingAssessmentUpdateServices;

  @Autowired
  public NursingAssessmentController(
          @Qualifier("NursingChartAddService") NursingAssessmentAddServices nursingAssessmentAddServices,
          @Qualifier("NursingChartUpdateService") NursingAssessmentUpdateServices nursingAssessmentUpdateServices
  ) {
    this.nursingAssessmentAddServices = nursingAssessmentAddServices;
    this.nursingAssessmentUpdateServices = nursingAssessmentUpdateServices;
  }

  @GetMapping
  public List<NursingAssessmentChartDto> getAllCharts(
          @RequestParam("patient_id") Long patientId
  ){
    return nursingAssessmentAddServices.getAllCharts(patientId);
  }

  @GetMapping("/{chart_id}")
  public NursingAssessmentChartDto getNursingAssessmentChartById(
          @PathVariable("chart_id") Long chartId
  ) {
    return nursingAssessmentAddServices.getNursingAssessmentChartById(chartId);
  }

  @PostMapping
  public ResponseEntity<?> addNursingChart(
          @RequestParam("patient_id") Long patientId,
          @RequestBody NursingAssessmentChartRequest request
  ) {
    return new ResponseEntity<>(
            nursingAssessmentAddServices.addNursingChart(patientId, request),
            HttpStatus.CREATED
    );
  }

  @PutMapping("/{chart_id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> updateNursingChart(
          @PathVariable("chart_id") Long chartId,
          @RequestBody NursingAssessmentChartRequest request
  ) {
    return ResponseEntity.ok(
            nursingAssessmentUpdateServices
                    .updateNursingChart(request, chartId)
    );
  }
}
