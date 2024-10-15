package com.azu.hospital.Patients.charts.patientAssessment;

import com.azu.hospital.Patients.charts.patientAssessment.dto.PatientAssessmentDto;
import com.azu.hospital.Patients.charts.patientAssessment.request.PatientAssessmentChartRequest;
import com.azu.hospital.Patients.charts.patientAssessment.services.PatientAssessmentService;
import com.azu.hospital.Patients.charts.patientAssessment.services.PatientAssessmentUpdateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/charts/patient-assessment-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER' , 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR' , 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF' , 'SURGICAL_HEAD_CHIEF' , 'INTERNAL_HEAD_CHIEF' , " +
        "'ANESTHETIC_HEAD_CHIEF' , 'PHYSICAL_THERAPY_MANAGER' , 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PatientAssessmentController {
    private final PatientAssessmentService service;
    private final PatientAssessmentUpdateService updateService;

    public PatientAssessmentController(
            @Qualifier("PatientAssessmentService") PatientAssessmentService service,
            @Qualifier("PatientAssessmentUpdateService")PatientAssessmentUpdateService updateService
    ) {
        this.service = service;
        this.updateService = updateService;
    }

    @GetMapping("/get-fluid")
    public ResponseEntity<?> getFluid() {
        return ResponseEntity.ok(service.getFluidList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PatientAssessmentDto> getAllCharts(
            @RequestParam("patient_id") Long patientId
    ){
        return service.getAllCharts(patientId);
    }

    @GetMapping("/{chart_id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientAssessmentDto getChartById(
            @PathVariable("chart_id") Long chartId
    ){
        return service.getChartById(chartId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewChart(
            @RequestParam("patient_id") Long patientId,
            @RequestBody PatientAssessmentChartRequest request
    ){
        return ResponseEntity.ok(service.createNewChart(patientId, request));
    }

    @PutMapping("/{chart_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> updateChart(
            @PathVariable("chart_id") Long chartId,
            @RequestBody PatientAssessmentChartRequest request
    ){
        return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
    }

}
