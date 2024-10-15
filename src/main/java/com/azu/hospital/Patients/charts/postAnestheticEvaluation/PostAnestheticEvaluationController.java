package com.azu.hospital.Patients.charts.postAnestheticEvaluation;

import com.azu.hospital.Patients.charts.postAnestheticEvaluation.dto.PostAnestheticEvaluationDto;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.request.PostAnestheticEvaluationRequest;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.services.PostAnestheticEvaluationService;
import com.azu.hospital.Patients.charts.postAnestheticEvaluation.services.PostAnestheticEvaluationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/charts/post-anesthetic-evaluation-chart")
@CrossOrigin
@PreAuthorize("hasAnyRole('HOSPITAL_MANAGER', 'HOSPITAL_ASSISTANCE_MANAGER' , 'DOCTOR', 'PERMANENT' , 'NURSES' " +
        ",'NURSING_HEAD_CHIEF', 'SURGICAL_HEAD_CHIEF', 'INTERNAL_HEAD_CHIEF', " +
        "'ANESTHETIC_HEAD_CHIEF', 'PHYSICAL_THERAPY_MANAGER', 'CHIEF_OPERATING_OFFICER' " +
        " )")
public class PostAnestheticEvaluationController {
    private final PostAnestheticEvaluationService service;
    private final PostAnestheticEvaluationUpdateService updateService;

    @Autowired
    public PostAnestheticEvaluationController(
            @Qualifier("PostAnestheticEvaluationService")
            PostAnestheticEvaluationService postAnestheticEvaluationService,
            @Qualifier("PostAnestheticEvaluationUpdateService")
            PostAnestheticEvaluationUpdateService updateService){
        this.service = postAnestheticEvaluationService;
        this.updateService = updateService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<PostAnestheticEvaluationDto> getAllChartsByPatientId(
            @RequestParam("patient_id") Long patientId
    ){
        return service.getAllChartsByPatientId(patientId);
    }

    @GetMapping("/{chart_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public PostAnestheticEvaluationDto getChartById(@PathVariable("chart_id") Long chartId){
        return service.getChartById(chartId);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> createNewChart(
            @RequestParam("patient_id") Long patientId,
            @RequestBody PostAnestheticEvaluationRequest request
    ){
        return ResponseEntity.ok(service.createNewChart(patientId, request));
    }

    @PutMapping("/{chart_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> updateExistsChart(
            @PathVariable("chart_id") Long chartId,
            @RequestBody PostAnestheticEvaluationRequest request
    ){
        return ResponseEntity.ok(updateService.updateExistsChart(chartId, request));
    }
}
